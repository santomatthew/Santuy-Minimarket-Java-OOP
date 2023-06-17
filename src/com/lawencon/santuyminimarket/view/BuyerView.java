package com.lawencon.santuyminimarket.view;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.santuyminimarket.model.Cart;
import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.History;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.BuyerService;
import com.lawencon.santuyminimarket.util.GenerateUtil;
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
			show();
		}
		else if(menuCode==2) {
			showCategoryList();
		}
		else if(menuCode==3) {
			showCart();
		}
		else if(menuCode==4) {
			mainView.show();
		}
		else if(menuCode==5) {
			System.out.println("Anda keluar aplikasi");
		}
		
	}
	
	//Show History
	private void showHistory() {
		for(int i=0;i<histories.size();i++) {
			System.out.println((i+1)+ ". Inv Code : "+ histories.get(i).getCodeInv() + " Grand Total : Rp." +histories.get(i).getGrandTotal());
		}
	}
	
	//Show Category List
	private void showCategoryList() {
		if(categories.size()==0) {
			System.out.println("Kategori Belum ada");
			show();
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
				System.out.println((i+1)+". "+ itemsByCategory.get(i).getItemName()+ " || Stocks : "+ itemsByCategory.get(i).getStocks());
			}
			
			final int chooseItem = ScannerUtil.scannerInt("Pilih item : ",1,itemsByCategory.size());
			//Masukkan item yang dipilih ke object baru
			final Item chosenItem = itemsByCategory.get(chooseItem-1);
			final int maximum = chosenItem.getStocks();
			final int quantity = ScannerUtil.scannerInt("Masukkan quantity : ", 1, maximum);
			boolean checkItem = buyerService.checkItem(carts, chosenItem.getItemName());
			
			if(checkItem) {
				//Update di cart
				final int takeField = buyerService.takeField(carts, chosenItem.getItemName());
				final Cart newCart = carts.get(takeField);
				newCart.setQuantity(newCart.getQuantity() + quantity);
				carts.set(takeField, newCart);
				
				//Update di Item
				final int itemField = buyerService.getItemFieldByName(items, newCart.getItemName());
				final Item newItem = items.get(itemField);
				newItem.setStocks(maximum-quantity);
				items.set(itemField, newItem);
				
				System.out.println("Berhasil menambah "+quantity+" "+ newCart.getItemName() );
			}
			else {
				final Cart newCart = new Cart();
				newCart.setItemName(chosenItem.getItemName());
				newCart.setCategoryName(chosenItem.getCategoryName());
				newCart.setPrice(chosenItem.getPrice());
				newCart.setQuantity(quantity);
				carts.add(newCart);
				
				//Update di Item
				final int itemField = buyerService.getItemFieldByName(items, newCart.getItemName());
				final Item newItem = items.get(itemField);
				newItem.setStocks(maximum-quantity);
				items.set(itemField, newItem);
				
				System.out.println("Berhasil membeli "+quantity+" "+ newCart.getItemName() );
			}
		}
		show();
		
	}
	
	private void printCart() {
		System.out.println("===== Keranjang =====");
		for(int i=0;i<carts.size();i++) {
			System.out.println((i+1)+". "
					+ carts.get(i).getItemName()
					+ " Quantity : "+ carts.get(i).getQuantity()
					+ " Price : Rp. "+ carts.get(i).getPrice());
		}
		System.out.println("===== Keranjang =====");
	}
	
	//Show Cart
	private void showCart() {
		if(carts.size()==0) {
			System.out.println("Keranjang anda masih kosong, silahkan membeli barang terlebih dahulu");
			show();
		}
		else {
			printCart();
			System.out.println("1. Update Quantity");
			System.out.println("2. Hapus Sebagian Belanjaan");
			System.out.println("3. Checkout");
			System.out.println("4. Kembali");
			
			final int cartMenu = ScannerUtil.scannerInt("Pilih menu :", 1, 4);
			cardOptionMenu(cartMenu);
		}
	}
	
	private void cardOptionMenu(int chosen) {
		if(chosen==1) {
			showUpdateQty();
		}
		else if(chosen==2) {
			showRemovePartial();
		}
		else if(chosen==3) {
			checkout();
		}
		else if(chosen==4) {
			show();
		}
	}
	
	
	private void showUpdateQty() {
		if(carts.size()==0) {
			System.out.println("Tidak ada barang dalam keranjang");
			show();
		}
		else {
			printCart();
			final int chooseItem = ScannerUtil.scannerInt("Pilih item yang mau diganti quantity : ", 1, carts.size());
			final int newQty = ScannerUtil.scannerNoMaximum("Masukkan quantity baru = ", 1);
			final Cart updatedCart = carts.get(chooseItem-1);
			if(updatedCart.getQuantity()> newQty) {
				final int giveBackStock = updatedCart.getQuantity()- newQty;
				final Cart updateNewCart = carts.get(chooseItem-1);
				updateNewCart.setQuantity(newQty);
				final int takeField = buyerService.getItemFieldByName(items, updatedCart.getItemName());
				
				// Update Stocks
				final Item updateItemStocks = items.get(takeField);
				updateItemStocks.setStocks(updateItemStocks.getStocks() + giveBackStock);
				items.set(takeField, updateItemStocks);
				carts.set(chooseItem-1, updateNewCart);
				
			}else if(updatedCart.getQuantity()< newQty){
				final int takeStock = newQty - updatedCart.getQuantity();
				final int stocks = buyerService.getMaximumStocks(items, carts.get(chooseItem-1).getItemName());
				if(stocks >= takeStock) {
					carts.set(chooseItem-1, updatedCart);
					System.out.println("Update berhasil");
				}
				else {
					System.out.println("Tidak bisa mengupdate quantity yang melebihi stok");
				}
			}
			showCart();
		}
		
		
		
	}
	
	private void showRemovePartial() {
		printCart();
		final int chooseToRemove = ScannerUtil.scannerInt("Pilih item yang mau dihapus : ", 1, carts.size());
		final Cart giveBackStocks = carts.get(chooseToRemove-1);
		final int takeField = buyerService.getItemFieldByName(items, giveBackStocks.getItemName());
		final Item updateStockItem = items.get(takeField);
		updateStockItem.setStocks(updateStockItem.getStocks() + giveBackStocks.getQuantity());
		items.set(takeField, updateStockItem);
		carts.remove(chooseToRemove-1);
		System.out.println("Item berhasil di hapus");
		showCart();
	}
	
	private void checkout() {
		final int grandTotal = buyerService.getGrandTotal(carts);
		final String invCode = GenerateUtil.generateRandom();
		
		History history = new History();
		history.setCodeInv(invCode);
		history.setGrandTotal(grandTotal);
		histories.add(history);
		carts.clear();
		System.out.println("Checkout berhasil");
		show();
	}
	
	
	
	
	
	
}
