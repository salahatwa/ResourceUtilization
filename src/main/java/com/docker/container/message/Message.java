package com.docker.container.message;

import java.io.Serializable;

/**
 * @author atwa Jun 24, 2018
 */
public class Message implements Serializable {
	private Header header;
	private Payload payload;

	public Message() {
		header = new Header();
		payload = new Payload();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}