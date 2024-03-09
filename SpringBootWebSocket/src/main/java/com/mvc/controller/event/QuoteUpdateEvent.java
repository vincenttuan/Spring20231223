package com.mvc.controller.event;

import org.springframework.context.ApplicationEvent;

import com.mvc.model.Quote;

public class QuoteUpdateEvent extends ApplicationEvent {
	
	private Quote quote;
	
	public QuoteUpdateEvent(Object source, Quote quote) {
		super(source);
		this.quote = quote;
	}
	
	public Quote getQuote() {
		return quote;
	}
	

}
