package com.lawencon.santuyminimarket.view;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.santuyminimarket.model.Cart;
import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.History;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.BuyerService;
import com.lawencon.santuyminimarket.util.ScannerUtil;

public class BuyerView {

	private final List<Cart> carts = new ArrayList<Cart>();
	private final BuyerService buyerService;
	
	private List<Category> categories;
	private List<Item> items;
	private List<History> histories;
	
	private final MainView mainView;
	
	BuyerView(BuyerService buyerService, MainView mainView){
		this.buyerService = buyerService;
		this.mainView = mainView;
	}
	
	void setCategories(List<Category>categories) {
		this.categories = categories;
	}
	
	void setItems(List<Item>items) {
		this.items = items;
	}
	
	void setHistories(List<History> histories) {
		this.histories = histories;
	}
	
	void show() {
		System.out.println("==== Welcome to Santuy Minimarket ====");
		System.out.println("1. History");
		System.out.println("2. Jenis Item Barang");
		System.out.println("3. Cart");
		System.out.println("4. Switch User");
		System.out.println("5. Keluar Aplikasi");
		
		final int chooseMenu = ScannerUtil.scannerInt("Pilih menu :" , 1, 5);
		menuOption(chooseMenu);
	}
	
	private void menuOption(int menuCode) {
		if(menuCode==1) {
			
		}
		if(menuCode==2) {
			
		}
		if(menuCode==3) {
			
		}
		if(menuCode==4) {
			
		}
		if(menuCode==5) {
			System.out.println("Anda keluar aplikasi");
		}
		
	}
	
}
