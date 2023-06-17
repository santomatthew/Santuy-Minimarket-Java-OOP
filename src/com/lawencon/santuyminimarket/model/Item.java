package com.lawencon.santuyminimarket.model;

public class Item {

	private String itemName;
	private String categoryName;
	private int price;
	private int stocks;
	private int itemCategoryId;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(int itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}


}
