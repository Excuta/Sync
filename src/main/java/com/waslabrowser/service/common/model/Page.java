package com.waslabrowser.service.common.model;

import lombok.Value;

@Value
public class Page {
	
	private Integer number;
	private Integer size;
	private Integer skip;
}