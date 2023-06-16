package com.lawencon.santuyminimarket.constant;

public enum Account {

	BUYER("pembeli","pembeli"),SELLER("penjual","penjual");
	
	public final String username;
	public String password;
	
	Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
}
