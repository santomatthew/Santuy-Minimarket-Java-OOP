package com.lawencon.santuyminimarket.service;

import java.util.List;

import com.lawencon.santuyminimarket.model.Cart;
import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.Item;

public interface BuyerService {

	List<Item> itemsByCategory(List<Item> items, int categoryId);
	Category getCategoryById(List<Category> categories, int categoryId);
	boolean checkItem(List<Cart> carts, String name);
	int takeField(List<Cart> carts, String name);
	
}
