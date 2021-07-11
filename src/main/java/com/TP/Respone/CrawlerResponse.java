package com.TP.Respone;
public class CrawlerResponse
{
	public enum State {
		PLAY,PAUSE,STOP,REFRESH;
	}
	int state;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}