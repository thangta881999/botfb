package com.TP.chatbot;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.send.MessengerSendClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class ChatBotApplication {

    private static final Logger logger = LoggerFactory.getLogger(ChatBotApplication.class);

    /**
     * Initializes the {@code MessengerSendClient}.
     *
     * @param pageAccessToken the generated {@code Page Access Token}
     */
    @Bean
    public MessengerSendClient messengerSendClient(@Value("EAADHFIeBIwABAKPCKfhawzZCyBqS4FrGjC1sUkKCxZCOB1hZBuiP38HOEemBZCKWjIom4sDcHrdvvp5rJWGcBw7CihAuxUY045ygdTjnX8DzQ32JSZAEBoWScwsBEs2mNm5pQ6uNFXD5tSU1i1QXSmCZBWV9yAfNgeDNKS3Mz55CdZCYjNJR4X7QL2xrXO5vSgZD") String pageAccessToken) {
        logger.debug("Initializing MessengerSendClient - pageAccessToken: {}", pageAccessToken);
        return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
    }
}
