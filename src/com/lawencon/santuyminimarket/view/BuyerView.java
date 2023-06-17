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
			showHistory();
		}
		if(menuCode==2) {
			showCategoryList();
		}
		if(menuCode==3) {
			showCart();
		}
		if(menuCode==4) {
			mainView.show();
		}
		if(menuCode==5) {
			System.out.println("Anda keluar aplikasi");
		}
		
	}
	
	//Show History
	private void showHistory() {
		
	}
	
	//Show Category List
	private void showCategoryList() {
		if(categories.size()==0) {
			System.out.println("Kategori Belum ada");
		}
		else {
			System.out.println("===== List Kategori Santuy Minimarket =====");
			for(int i=0;i<categories.size();i++) {
				System.out.println((i+1)+ ". "+ categories.get(i).getCategoryName());
			}
			System.out.println("===== List Kategori Santuy Minimarket =====");
			
			final int chooseCategory = ScannerUtil.scannerInt("Pilih kategori : ", 1, categories.size());
			final int categoryId = categories.get(chooseCategory-1).getCategoryId();
			showItemsByCategory(categoryId);
		}
	}
	
	//Show Item By Category
	private void showItemsByCategory(int categoryId) {
		final Category category = buyerService.getCategoryById(categories, categoryId);
		final List<Item> itemsByCategory = buyerService.itemsByCategory(items, categoryId);
		if(itemsByCategory.size()==0) {
			System.out.println("Item pada kategori "+ category.getCategoryName()+ " kosong");
		}
		else {
			System.out.println("===== List Item Kategori "+ category.getCategoryName() +" =====");
			for(int i=0;i<itemsByCategory.size();i++) {
				System.out.println((i+1)+". "+ itemsByCategory.get(i).getItemName());
			}
			
			final int chosenItem =  ScannerUtil.scannerInt("Pilih item :", 1, itemsByCategory.size());
			final int quantity = ScannerUtil.scannerNoMaximum("Masukkan Quantity: ", 1);
			final String itemName= items.get(chosenItem-1).getItemName();
			Cart cart = new Cart();
			final boolean checkItem = buyerService.checkItem(carts, itemName);
			if(checkItem) {
				final int takeField = buyerService.takeField(carts, itemName);
				cart = carts.get(takeField);
				cart.setQuantity(cart.getQuantity()+ quantity);
				carts.set(takeField, cart);
				System.out.println("Berhasil menambah "+quantity+" "+ itemName );
			}
			else {
				cart.setItemName(itemsByCategory.get(chosenItem-1).getItemName());
				cart.setCategoryName(category.getCategoryName());
				cart.setPrice(itemsByCategory.get(chosenItem-1).getPrice());
				cart.setQuantity(quantity);
				carts.add(cart);
				System.out.println("Berhasil membeli "+quantity+" "+ itemName );
			}
			
		}
		show();
		
	}
	
	//Show Cart
	private void showCart() {
		if(carts.size()==0) {
			System.out.println("Keranjang anda masih kosong, silahkan membeli barang terlebih dahulu");
		}
		else {
			System.out.println("===== Keranjang =====");
			for(int i=0;i<carts.size();i++) {
				System.out.println((i+1)+". "
						+ carts.get(i).getItemName()
						+ " Quantity : "+ carts.get(i).getQuantity()
						+ " Price : Rp. "+ carts.get(i).getPrice());
			}
			System.out.println("===== Keranjang =====");
		
		System.out.println("1. Update Quantity");
		System.out.println("2. Hapus Sebagian Belanjaan");
		System.out.println("3. Kembali");
		}
	}
	
	
	
	
}
