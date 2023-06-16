package com.lawencon.santuyminimarket.view;

import com.lawencon.santuyminimarket.util.ScannerUtil;

public class BuyerView {

	
	void show() {
		System.out.println("==== Welcome to Santuy Minimarket ====");
		System.out.println("1. History");
		System.out.println("2. Jenis Item Barang");
		System.out.println("3. Cart");
		System.out.println("4. Checkout");
		System.out.println("5. Switch User");
		System.out.println("5. Keluar Aplikasi");
		
		final int chooseMenu = ScannerUtil.scannerInt("Pilih menu :" , 1, 5);
		menuOption(chooseMenu);
	}
	
	private void menuOption(int menuCode) {
		
	}
	
}
