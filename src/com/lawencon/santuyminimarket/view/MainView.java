package com.lawencon.santuyminimarket.view;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.History;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.BuyerService;
import com.lawencon.santuyminimarket.service.LoginService;
import com.lawencon.santuyminimarket.service.SellerService;
import com.lawencon.santuyminimarket.service.impl.BuyerServiceImpl;
import com.lawencon.santuyminimarket.service.impl.LoginServiceImpl;
import com.lawencon.santuyminimarket.service.impl.SellerServiceImpl;
import com.lawencon.santuyminimarket.util.ScannerUtil;

public class MainView {

	private final List<Category> categories = new ArrayList<Category>();
	private final List<Item> items = new ArrayList<Item>();
	private final List<History> histories = new ArrayList<History>();
	
	 public void show() {
		 System.out.println("===== Santuy Minimarket Login=====");
		 final String username = ScannerUtil.scannerStr("Username: ");
		 final String password = ScannerUtil.scannerStr("Password: ");
	
		 final LoginService loginService = new LoginServiceImpl();
		 final int accountCheck = loginService.login(username, password);
		 
		 if(accountCheck ==1) {
			 final BuyerService buyerService= new BuyerServiceImpl();
			 final BuyerView buyerView = new BuyerView(buyerService,this);
			 buyerView.setCategories(categories);
			 buyerView.setHistories(histories);
			 buyerView.setItems(items);
			 buyerView.show();
			 
		 }
		 else if(accountCheck==2) {
			 final SellerService sellerService = new SellerServiceImpl();
			 final SellerView sellerView =new SellerView(sellerService,this);
			 sellerView.setCategories(categories);
			 sellerView.setHistories(histories);
			 sellerView.setItems(items);
			 sellerView.show();
		 }
		 else {
			 System.out.println("Username atau password salah");
			 show();
		 }
		
		 
	}
	
}
