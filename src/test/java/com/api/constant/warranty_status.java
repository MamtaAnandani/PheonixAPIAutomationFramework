package com.api.constant;

public enum warranty_status {

	IN_WARRANTY(1), OUT_WARRANTY(2);
	
	private int code;

	private warranty_status(int code) {
		this.code= code;
		}
	
	public int getCode(){
		return code;
	}
	
	}
