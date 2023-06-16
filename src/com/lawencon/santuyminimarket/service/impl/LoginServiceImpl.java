package com.lawencon.santuyminimarket.service.impl;

import com.lawencon.santuyminimarket.constant.Account;
import com.lawencon.santuyminimarket.service.LoginService;

public class LoginServiceImpl implements LoginService{

	@Override
	public int login(String username ,String password) {
		final Account[] accounts = Account.values();
		int result = 0;
		
		if(accounts[0].username.equals(username) && accounts[0].password.equals(password)) {
			result =1;
		}
		else if(accounts[1].username.equals(username) && accounts[1].password.equals(password)) {
			result=2;
		}
		
		return result;
	}	
}
