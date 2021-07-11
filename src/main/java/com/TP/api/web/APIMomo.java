package com.TP.api.web;

import com.TP.DTO.MoMoPayment;
import com.TP.service.ChiTietHoaDonService;
import com.TP.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/")
public class APIMomo {

	@Autowired
	HoaDonService hoaDonService;

	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	
	
	 public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
	    {  
	        // Static getInstance method is called with hashing SHA  
	        MessageDigest md = MessageDigest.getInstance("SHA-256");  
	  
	        // digest() method called  
	        // to calculate message digest of an input  
	        // and return array of byte 
	        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
	    } 
	    
	    public static String toHexString(byte[] hash) 
	    { 
	        // Convert byte array into signum representation  
	        BigInteger number = new BigInteger(1, hash);  
	  
	        // Convert message digest into hex value  
	        StringBuilder hexString = new StringBuilder(number.toString(16));  
	  
	        // Pad with leading zeros 
	        while (hexString.length() < 32)  
	        {  
	            hexString.insert(0, '0');  
	        }  
	  
	        return hexString.toString();  
	    } 
	@GetMapping(path = "informMomo")
	@ResponseBody
	public MoMoPayment findInformMomo() throws NoSuchAlgorithmException {
		
		MoMoPayment momoPayment= new MoMoPayment();
		momoPayment.setPartnerCode("MOMOAU8Q20200716");
		momoPayment.setAccessKey("ilANtDiIzveQL5uT");
		momoPayment.setAmount("1000");
		momoPayment.setExtraData("merchantName=Payment");
		String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
		momoPayment.setOrderId(orderId);
		momoPayment.setOrderInfo("Thanh toán hóa đơn :" + momoPayment.getOrderId());
		momoPayment.setRequestId(requestId);
		momoPayment.setRequestType("captureMoMoWallet");
		momoPayment.setNotifyurl("https://momo.vn/");
		momoPayment.setReturnUrl("http://localhost:8080/");
		
		String secretkey="tnDS9qzHmYj0FQVz2PeR6MsDjWlRE2Er";
		
		String rawSignature= "partnerCode="+momoPayment.getPartnerCode()+"&accessKey="+momoPayment.getAccessKey()+"&requestId="+momoPayment.getRequestId()+"&amount="+momoPayment.getAmount()+"&orderId="+momoPayment.getOrderId()+"&orderInfo="+momoPayment.getOrderInfo()+"&returnUrl="+momoPayment.getReturnUrl()+"&notifyUrl="+momoPayment.getNotifyurl()+"&extraData="+momoPayment.getExtraData();
		momoPayment.setSignature(toHexString(getSHA(rawSignature)));
		return momoPayment;


	}
}
