package com.lawencon.santuyminimarket.view;

import java.util.ArrayList;
import java.util.List;
import com.lawencon.santuyminimarket.model.Cart;
import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.History;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.SellerService;
import com.lawencon.santuyminimarket.util.ScannerUtil;

public class SellerView {

	private final List<Cart> carts = new ArrayList<Cart>();
	private final SellerService sellerService ;
	
	
	private List<Category> categories;
	private List<Item> items;
	private List<History> histories;
	
	private final MainView mainView;
	
	
	SellerView(SellerService sellerService, MainView mainView){
		this.sellerService = sellerService;
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
		System.out.println("===== Welcome Seller =====");
		System.out.println("1. View All Jenis Item");
		System.out.println("2. View Transaksi Pembeli");
		System.out.println("3. Switch User");
		System.out.println("4. Keluar Aplikasi");
		
		final int chooseMenu = ScannerUtil.scannerInt("Pilih Menu : ", 1, 4);
		if(chooseMenu==1) {
			showCategoryMenu();
		}
		else if(chooseMenu==2) {
			showHistories();
		}
		else if(chooseMenu==3) {
			mainView.show();
		}
		else if(chooseMenu==4) {
			System.out.println("Anda Keluar Aplikasi");
		}
	}
	
	//Show List Category
	private void showCategories() {
		if(categories.size()==0) {
			System.out.println("Kategori belum ada, silahkan menambahkan kategori");
		}
		else {
			System.out.println("===== List Kategori Santuy Minimarket =====");
			for(int i=0;i<categories.size();i++) {
				System.out.println((i+1)+ ". "+ categories.get(i).getCategoryName());
			}
			System.out.println("===== List Kategori Santuy Minimarket =====");
		}
	}
	
	private void showCategoryMenu() {
		showCategories();
		System.out.println("1. Tambah Kategori");
		System.out.println("2. Hapus Kategori");
		System.out.println("3. Edit Kategori");
		System.out.println("4. Masuk ke Kategori (Untuk melihat list item berdasarkan kategori");
		System.out.println("5. Kembali");
		final int chooseMenu = ScannerUtil.scannerInt("Pilih Menu : ", 1, 5);
		categoryOption(chooseMenu);
		
	}
	
	private void categoryOption(int option) {
		if(option ==1) {
			showAddCategory();
		}
		if(option ==2) {
			showRemoveCategory();
		}
		if(option ==3) {
			showEditCategory();
		}
		if(option ==4) {
			showSelectCategory();
		}
		if(option ==5) {
			show();
		}
	}
	
	//Add Category
	private void showAddCategory() {
		final String categoryName = ScannerUtil.scannerStr("Masukkan nama kategori : ");
		final boolean categoryChecker = sellerService.checkCategoryIfExist(categories, categoryName);
		if(categoryChecker) {
			System.out.println("Nama kategori sudah ada, silahkan menambahkan yang baru");
			showAddCategory();
		}
		else {
			final Category newCategory = new Category();
			newCategory.setCategoryName(categoryName);
			categories.add(newCategory);
			System.out.println("==== Response ====");
			System.out.println("System res: (Sukses menambahkan kategori "+ categoryName+")");
			System.out.println("==== Response ====");
			showCategoryMenu();
		}
	}
	
	//Remove Category
	private void showRemoveCategory() {
		if(categories.size()==0) {
			System.out.println("==== Response ====");
			System.out.println("System res: (Tidak ada kategori yang bisa dihapus)");
			System.out.println("==== Response ====");
		}
		else {
			showCategories();
			final int chosenCategory = ScannerUtil.scannerInt("Pilih kategori yang mau dihapus : ", 1, categories.size());
			categories.remove(chosenCategory-1);
			System.out.println("==== Response ====");
			System.out.println("System res: (Kategori berhasil di hapus)");
			System.out.println("==== Response ====");
		}
		showCategoryMenu();
	}
	
	//Edit Category
	private void showEditCategory() {
		if(categories.size()==0) {
			System.out.println("==== Response ====");
			System.out.println("System res: (Tidak ada kategori yang bisa diedit)");
			System.out.println("==== Response ====");
		}
		else {
			showCategories();
			final int chosenCategory = ScannerUtil.scannerInt("Pilih kategori yang mau diedit : ", 1, categories.size());
			final String newCategoryName = ScannerUtil.scannerStr("Masukkan nama kategori baru : ");
			final Category categoryNew = categories.get(chosenCategory-1);
			categoryNew.setCategoryName(newCategoryName);
			categories.set(chosenCategory-1, categoryNew);
			System.out.println("==== Response ====");
			System.out.println("System : Nama kategori berhasil di update");
			System.out.println("==== Response ====");
		}
		showCategoryMenu();
	}
	
	//Select Category
	private void showSelectCategory() {
		if(categories.size()==0) {
			System.out.println("==== Response ====");
			System.out.println("System res: (Tidak ada kategori yang bisa dipilih)");
			System.out.println("==== Response ====");
		}
		else {
			showCategories();
			final int choseCategory = ScannerUtil.scannerInt("Pilih list item berdasarkan kategori :", 1, categories.size());
			showAllItemsByCategory(choseCategory-1);
		}
		
		
	}
	
	//Show All Items by Category
	private void showAllItemsByCategory(int categoryId) {
		final String categoryName = categories.get(categoryId).getCategoryName();
		//line checkItemInCategory kemungkinan bug
		final boolean checkItemInCategory = sellerService.checkItemCategory(items, categoryName);
		
		if(!checkItemInCategory) {
			System.out.println("Item di kategori "+ categoryName+ " belum ada");
		}
		else {
			final List<Item>listItemByCategory = sellerService.getItemByCategoryName(categoryName, items); 
			System.out.println("===== List Item =====");
			for(int i=0;i<listItemByCategory.size();i++) {
				System.out.println((i+1)+". "+ listItemByCategory.get(i).getItemName()
						+" || Harga : Rp."+ listItemByCategory.get(i).getPrice() 
						+" || Stok : "+ listItemByCategory.get(i).getStocks());
			}
			System.out.println("===== List Item =====");
		}
		
		System.out.println("1. Tambah Item");
		System.out.println("2. Hapus Item");
		System.out.println("3. Ubah Kategori");
		System.out.println("4. Edit Item");
		System.out.println("5. Kembali");
		final int chooseItemMenu= ScannerUtil.scannerInt("Pilih Menu : ", 1, 5);
		itemOption(chooseItemMenu,categoryId);
		
	}
	
	//Item option menu
	private void itemOption(int menuCode, int categoryId) {
		if(menuCode==1) {
			showAddNewItem(categoryId);
		}
		if(menuCode==2) {
			showDeleteItem(categoryId);
		}
		if(menuCode==3) {
			
		}
		if(menuCode==4) {
			
		}
		if(menuCode==5) {
			showCategoryMenu();
		}
		
	}
	
	//Add new Item
	private void showAddNewItem(int categoryId) {
		final String newItemName = ScannerUtil.scannerStr("Masukkan nama : ");
		final String categoryName = categories.get(categoryId).getCategoryName();
		final int price = ScannerUtil.scannerNoMaximum("Masukkan harga : ", 1000);
		final int stocks = ScannerUtil.scannerNoMaximum("Masukkan Stok :", 1);
		final Item newItem = new Item();
		newItem.setItemName(newItemName);
		newItem.setCategoryName(categoryName);
		newItem.setPrice(price);
		newItem.setStocks(stocks);
		items.add(newItem);
		
		System.out.println("==== Response ====");
		System.out.println("System res: Item "+ newItemName+ " "
				+ "dengan harga satuan Rp."+ price 
				+ " sebanyak : "+stocks 
				+" stok berhasil ditambahkan" );
		System.out.println("==== Response ====");
		showAllItemsByCategory(categoryId);
	}
	
	//Delete Item
	private void showDeleteItem(int categoryId) {
		final String categoryName = categories.get(categoryId).getCategoryName();
		final boolean checkItemInCategory = sellerService.checkItemCategory(items, categoryName);
		if(!checkItemInCategory) {
			System.out.println("==== Response ====");
			System.out.println("System res: (Tidak ada item yang dapat dihapus di kategori "+categoryName + " )");
			System.out.println("==== Response ====");
		}
		else {
			final List<Item>listItemByCategory = sellerService.getItemByCategoryName(categoryName, items); 
			for(int i=0;i<listItemByCategory.size();i++) {
				System.out.println((i+1)+". "+ listItemByCategory.get(i).getItemName());
			}
			final int chosenItemToDelete = ScannerUtil.scannerInt("Pilih item yang mau dihapus : ", 1, listItemByCategory.size());
			final String getItemName = listItemByCategory.get(chosenItemToDelete-1).getItemName();
			final int takeItemField = sellerService.removeItemByName(items, getItemName);
			items.remove(takeItemField);
			System.out.println("==== Response ====");
			System.out.println("System res: (Item "+getItemName +" berhasil di hapus");
			System.out.println("==== Response ====");
			
		}
		
		showAllItemsByCategory(categoryId);
	}
	
	//Show Histories
	private void showHistories() {
		
	}

	
}
