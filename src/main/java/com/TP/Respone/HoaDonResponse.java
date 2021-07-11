package com.TP.Respone;

import java.util.Map;

public class HoaDonResponse {
	 private boolean validated;
	    private Map<String, String> errorMessages;
	    private int id;
	 
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isValidated() {
	        return validated;
	    }
	 
	    public void setValidated(boolean validated) {
	        this.validated = validated;
	    }
	 
	    public void setErrorMessages(Map<String, String> errorMessages) {
	        this.errorMessages = errorMessages;
	    }
	 
	    public Map<String, String> getErrorMessages() {
	        return errorMessages;
	    }

}
