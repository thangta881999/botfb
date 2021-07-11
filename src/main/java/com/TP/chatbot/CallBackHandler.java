package com.TP.chatbot;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.exceptions.MessengerVerificationException;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.receive.events.AccountLinkingEvent;
import com.github.messenger4j.receive.handlers.*;
import com.github.messenger4j.send.*;
import com.github.messenger4j.send.buttons.Button;
import com.github.messenger4j.send.templates.GenericTemplate;
import com.github.messenger4j.send.templates.ButtonTemplate;
import com.github.messenger4j.send.QuickReply;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/callback")
public class CallBackHandler {
    private static final Logger logger = LoggerFactory.getLogger(CallBackHandler.class);


//    public static final String GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_GOOD_ACTION";
//    public static final String NOT_GOOD_ACTION = "DEVELOPER_DEFINED_PAYLOAD_FOR_NOT_GOOD_ACTION";
//    private static final String RESOURCE_URL = "https://raw.githubusercontent.com/fbsamples/messenger-platform-samples/master/node/public";

    public static final String danhmuc1 = "Áo Sơ Mi";
    public static final String danhmuc2 = "Áo Thun";
    public static final String danhmuc3 = "Quần Short";
    public static final String danhmuc4 = "Áo Khoác";
    public static final String danhmuc5 = "Túi đeo";
    public static final String danhmuc6 = "Quần jean";
    public static final String danhmuc7 = "Quần kaki";

    public static final String STATE1 = "Loại sản phẩm";
    public static final String STATE2 = "Tư vấn thêm";
    public static final String STATE3 = "Đặt ngay";
    public static final String STATE4 = "Kết thúc";
    public static final String STATE5 = "Reset";

    public static final String confirm1 = "1. Yes";
    public static final String confirm2 = "2. No";


    public final String appSecret = "5b151aac813b854bdacb026ae9c31772";


    private final MessengerReceiveClient receiveClient;
    private final MessengerSendClient sendClient;

    @Autowired
    public CallBackHandler(@Value("5b151aac813b854bdacb026ae9c31772") final String appSecret,
                           @Value("123456789") final String verifyToken,
                           final MessengerSendClient sendClient) {

        logger.debug("Initializing MessengerReceiveClient - appSecret: {} | verifyToken: {}", appSecret, verifyToken);
        this.receiveClient = MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken)
                .onTextMessageEvent(newTextMessageEventHandler())
                .onQuickReplyMessageEvent(newQuickReplyMessageEventHandler())
                .onPostbackEvent(newPostbackEventHandler())
                .onAccountLinkingEvent(newAccountLinkingEventHandler())
                .onOptInEvent(newOptInEventHandler())
                .onEchoMessageEvent(newEchoMessageEventHandler())
                .onMessageDeliveredEvent(newMessageDeliveredEventHandler())
                .onMessageReadEvent(newMessageReadEventHandler())
                .fallbackEventHandler(newFallbackEventHandler())
                .build();
        this.sendClient = sendClient;
    }

    /**
     * Webhook verification endpoint.
     *
     * The passed verification token (as query parameter) must match the configured verification token.
     * In case this is true, the passed challenge string must be returned by this endpoint.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") final String mode,
                                                @RequestParam("hub.verify_token") final String verifyToken,
                                                @RequestParam("hub.challenge") final String challenge) {

        logger.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode,
                verifyToken, challenge);
        try {
            return ResponseEntity.ok(this.receiveClient.verifyWebhook(mode, verifyToken, challenge));
        } catch (MessengerVerificationException e) {
            logger.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    /**
     * Callback endpoint responsible for processing the inbound messages and events.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
                                               @RequestHeader("X-Hub-Signature") final String signature) {

        logger.debug("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
        try {
            this.receiveClient.processCallbackPayload(payload, signature);
            logger.debug("Processed callback payload successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (MessengerVerificationException e) {
            logger.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private TextMessageEventHandler newTextMessageEventHandler() {
        return event -> {
            logger.debug("Received TextMessageEvent: {}", event);

            final String messageId = event.getMid();
            final String messageText = event.getText();
            final String senderId = event.getSender().getId();
            final Date timestamp = event.getTimestamp();

            logger.info("Received message '{}' with text '{}' from user '{}' at '{}'",
                    messageId, messageText, senderId, timestamp);

            try {
                switch (messageText.toLowerCase()) {


                    case "Bắt đầu":
                        sendTextMessage(senderId, "Xin chào, Bạn cần tôi giúp gì ạ ? ");
                        break;

                    case "Tư vấn thêm":
                        sendTextMessage(senderId, "Xin mời nhập vấn đề bạn cần hỏi và vui lòng chờ đợi tư vấn viên trả lời ");
                        sendReadReceipt(senderId);
                        sendTypingOn(senderId);
                        sendTypingOff(senderId);
                        break;

                    case "Kết thúc":
                        sendTextMessage(senderId, "Cảm ơn anh/chị đã quan tâm tới shop. Mong anh/chị tiếp tục ủng hộ shop trong thời gian tiếp theo");
                        break;

                    case "Loại sản phẩm":
                        sendTextMessage(senderId, "Lựa chọn loại sản phẩm mong muốn");
                        sendQuickReplydanhmuc(senderId);
                        break;

                    default:
                        sendTextMessage(senderId,messageText);
                        sendReadReceipt(senderId);
                        sendTypingOn(senderId);
                        sendSpringDoc(senderId, messageText);
                        sendQuickReply(senderId);
                        sendTypingOff(senderId);
                }
            } catch (MessengerApiException | MessengerIOException e) {
                handleSendException(e);
            } catch (IOException e) {
                handleIOException(e);
            }

        };

    }

    private void sendButtonMessage(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<Button> buttons = Button.newListBuilder()
                .addUrlButton("Open Web URL", "https://www.oculus.com/en-us/rift/").toList()
                .addPostbackButton("Trigger Postback", "DEVELOPER_DEFINED_PAYLOAD").toList()
                .addCallButton("Call Phone Number", "+16505551234").toList()
                .build();

        final ButtonTemplate buttonTemplate = ButtonTemplate.newBuilder("Tap a button", buttons).build();
        this.sendClient.sendTemplate(recipientId, buttonTemplate);
    }

    private void sendSpringDoc(String recipientId, String keyword) throws MessengerApiException, MessengerIOException, IOException {

        Document doc = Jsoup.connect(("https://spring.io/search?q=").concat(keyword)).get();
        String countResult = doc.select("div.search-results--count").first().ownText();
        Elements searchResult = doc.select("section.search-result");
        List<SearchResult> searchResults = searchResult.stream().map(element ->
                new SearchResult(element.select("a").first().ownText(),
                        element.select("a").first().absUrl("href"),
                        element.select("div.search-result--subtitle").first().ownText(),
                        element.select("div.search-result--summary").first().ownText())
        ).limit(3).collect(Collectors.toList());

        final List<Button> firstLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(0).getLink()).toList()
                .build();
        final List<Button> secondLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(1).getLink()).toList()
                .build();
        final List<Button> thirdtLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(2).getLink()).toList()
                .build();
        final List<Button> searchLink = Button.newListBuilder()
                .addUrlButton("Open Link", ("https://spring.io/search?q=").concat(keyword)).toList()
                .build();



        final GenericTemplate genericTemplate = GenericTemplate.newBuilder()
                .addElements()
                    .addElement(searchResults.get(0).getTitle())
                    .subtitle(searchResults.get(0).getSubtitle())
                    .itemUrl(searchResults.get(0).getLink())
                    .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                    .buttons(firstLink)
                    .toList()
                .addElement(searchResults.get(1).getTitle())
                    .subtitle(searchResults.get(1).getSubtitle())
                    .itemUrl(searchResults.get(1).getLink())
                    .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                    .buttons(secondLink)
                    .toList()
                .addElement(searchResults.get(2).getTitle())
                    .subtitle(searchResults.get(2).getSubtitle())
                    .itemUrl(searchResults.get(2).getLink())
                    .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                    .buttons(thirdtLink)
                    .toList()
                .addElement("All results " + countResult)
                    .subtitle("Spring Search Result")
                    .itemUrl(("https://spring.io/search?q=").concat(keyword))
                    .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                    .buttons(searchLink)
                    .toList()
                .done()
                .build();

        this.sendClient.sendTemplate(recipientId, genericTemplate);
    }



    // anh gif
    private void sendGifMessage(String recipientId, String gif) throws MessengerApiException, MessengerIOException {
        this.sendClient.sendImageAttachment(recipientId, gif);
    }

    //send cac lua chon tra loi

    private void sendQuickReply(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Đúng đấy!", confirm1).toList()
                .addTextQuickReply("Không phải!", confirm2).toList()
                .build();

        this.sendClient.sendTextMessage(recipientId, "Có phải thứ bạn muốn tìm?", quickReplies);

    }

    private void sendQuickReplystate(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Loại sản phẩm", STATE1).toList()
                .addTextQuickReply("Tư vấn thêm", STATE2).toList()
                .addTextQuickReply("Đặt mua", STATE3).toList()
                .addTextQuickReply("Kết thúc", STATE4).toList()
                .build();

        this.sendClient.sendTextMessage(recipientId, "Xin mời lựa chọn yêu cầu bạn cần", quickReplies);

    }

    private void sendQuickReplystate1(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Có", STATE5).toList()
                .addTextQuickReply("Kết thúc", STATE4).toList()
                .build();
    }

    private void sendQuickReplydanhmuc(String recipientId) throws MessengerApiException, MessengerIOException {
        final List<QuickReply> quickReplies = QuickReply.newListBuilder()
                .addTextQuickReply("Áo Sơ Mi", danhmuc1).toList()
                .addTextQuickReply("Áo Thun", danhmuc2).toList()
                .addTextQuickReply("Quần Short", danhmuc3).toList()
                .addTextQuickReply("Áo Khoác", danhmuc4).toList()
                .addTextQuickReply("Túi Đeo", danhmuc5).toList()
                .addTextQuickReply("Quần Jean", danhmuc6).toList()
                .addTextQuickReply("Quần Kaki", danhmuc7).toList()
                .addTextQuickReply("Không có", confirm2).toList()
                .build();

        this.sendClient.sendTextMessage(recipientId, "Lựa chọn loại sản phẩm bạn cần tìm", quickReplies);

    }

    private void sendReadReceipt(String recipientId) throws MessengerApiException, MessengerIOException {
        this.sendClient.sendSenderAction(recipientId, SenderAction.MARK_SEEN);
    }

    private void sendTypingOn(String recipientId) throws MessengerApiException, MessengerIOException {
        this.sendClient.sendSenderAction(recipientId, SenderAction.TYPING_ON);
    }

    private void sendTypingOff(String recipientId) throws MessengerApiException, MessengerIOException {
        this.sendClient.sendSenderAction(recipientId, SenderAction.TYPING_OFF);
    }

    private QuickReplyMessageEventHandler newQuickReplyMessageEventHandler() {
        return event -> {
            logger.debug("Received QuickReplyMessageEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String messageId = event.getMid();
            final String quickReplyPayload = event.getQuickReply().getPayload();

            logger.info("Received quick reply for message '{}' with payload '{}'", messageId, quickReplyPayload);

            try {
                if(messageId.equalsIgnoreCase("tư vấn viên")==true)
                    {sendTextMessage(senderId,"Đang chờ tư vấn viên . . .");}
                if(quickReplyPayload.equals(confirm1))
                    {sendGifMessage(senderId, "https://media.giphy.com/media/3oz8xPxTUeebQ8pL1e/giphy.gif");}
                else if(quickReplyPayload.equals(confirm2))
                    {sendGifMessage(senderId, "https://media.giphy.com/media/26ybx7nkZXtBkEYko/giphy.gif");}
                else if(quickReplyPayload.equals(STATE1))
                    { sendTextMessage(senderId, "Lựa chọn loại sản phẩm mong muốn");
                        sendQuickReplydanhmuc(senderId);}
                else if(quickReplyPayload.equals(STATE4))
                    {return;}
                else if(quickReplyPayload.equals(STATE2))
                {
                    sendTextMessage(senderId, "Xin mời nhập vấn đề bạn cần hỏi và vui lòng chờ đợi tư vấn viên trả lời ");
                    sendReadReceipt(senderId);
                    sendTypingOn(senderId);
                    sendTypingOff(senderId);
                }
            } catch (MessengerApiException e) {
                handleSendException(e);
            } catch (MessengerIOException e) {
                handleIOException(e);
            }

            sendTextMessage(senderId, "Bạn có muốn thử lại với một yêu cầu khác không");
            try {
                sendQuickReplystate1(senderId);
            } catch (MessengerApiException e) {
                e.printStackTrace();
            } catch (MessengerIOException e) {
                e.printStackTrace();
            }
        };
    }

    private PostbackEventHandler newPostbackEventHandler() {
        return event -> {
            logger.debug("Received PostbackEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String recipientId = event.getRecipient().getId();
            final String payload = event.getPayload();
            final Date timestamp = event.getTimestamp();

            logger.info("Received postback for user '{}' and page '{}' with payload '{}' at '{}'",
                    senderId, recipientId, payload, timestamp);

            sendTextMessage(senderId, "Postback called");
        };
    }

    private AccountLinkingEventHandler newAccountLinkingEventHandler() {
        return event -> {
            logger.debug("Received AccountLinkingEvent: {}", event);

            final String senderId = event.getSender().getId();
            final AccountLinkingEvent.AccountLinkingStatus accountLinkingStatus = event.getStatus();
            final String authorizationCode = event.getAuthorizationCode();

            logger.info("Received account linking event for user '{}' with status '{}' and auth code '{}'",
                    senderId, accountLinkingStatus, authorizationCode);
        };
    }

    private OptInEventHandler newOptInEventHandler() {
        return event -> {
            logger.debug("Received OptInEvent: {}", event);

            final String senderId = event.getSender().getId();
            final String recipientId = event.getRecipient().getId();
            final String passThroughParam = event.getRef();
            final Date timestamp = event.getTimestamp();

            logger.info("Received authentication for user '{}' and page '{}' with pass through param '{}' at '{}'",
                    senderId, recipientId, passThroughParam, timestamp);

            sendTextMessage(senderId, "Authentication successful");
        };
    }

    private EchoMessageEventHandler newEchoMessageEventHandler() {
        return event -> {
            logger.debug("Received EchoMessageEvent: {}", event);

            final String messageId = event.getMid();
            final String recipientId = event.getRecipient().getId();
            final String senderId = event.getSender().getId();
            final Date timestamp = event.getTimestamp();

            logger.info("Received echo for message '{}' that has been sent to recipient '{}' by sender '{}' at '{}'",
                    messageId, recipientId, senderId, timestamp);
        };
    }

    private MessageDeliveredEventHandler newMessageDeliveredEventHandler() {
        return event -> {
            logger.debug("Received MessageDeliveredEvent: {}", event);

            final List<String> messageIds = event.getMids();
            final Date watermark = event.getWatermark();
            final String senderId = event.getSender().getId();

            if (messageIds != null) {
                messageIds.forEach(messageId -> {
                    logger.info("Received delivery confirmation for message '{}'", messageId);
                });
            }

            logger.info("All messages before '{}' were delivered to user '{}'", watermark, senderId);
        };
    }

    private MessageReadEventHandler newMessageReadEventHandler() {
        return event -> {
            logger.debug("Received MessageReadEvent: {}", event);

            final Date watermark = event.getWatermark();
            final String senderId = event.getSender().getId();

            logger.info("All messages before '{}' were read by user '{}'", watermark, senderId);
        };
    }


    /**
     * This handler is called when either the message is unsupported or when the event handler for the actual event type
     * is not registered. In this showcase all event handlers are registered. Hence only in case of an
     * unsupported message the fallback event handler is called.
     */
    private FallbackEventHandler newFallbackEventHandler() {
        return event -> {
            logger.debug("Received FallbackEvent: {}", event);

            final String senderId = event.getSender().getId();
            logger.info("Received unsupported message from user '{}'", senderId);
        };
    }

    private void sendTextMessage(String recipientId, String text) {
        try {
            final Recipient recipient = Recipient.newBuilder().recipientId(recipientId).build();
            final NotificationType notificationType = NotificationType.REGULAR;
            final String metadata = "DEVELOPER_DEFINED_METADATA";

            this.sendClient.sendTextMessage(recipient, notificationType, text, metadata);
        } catch (MessengerApiException | MessengerIOException e) {
            handleSendException(e);
        }
    }

    private void handleSendException(Exception e) {
        logger.error("Không thể gửi tin nhắn. Đã có lỗi xảy ra", e);
    }

    private void handleIOException(Exception e) {
        logger.error("Không thể mở. Đã có lỗi xảy ra", e);
    }

}
