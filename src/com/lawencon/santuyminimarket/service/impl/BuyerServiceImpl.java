package com.lawencon.santuyminimarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.santuyminimarket.model.Cart;
import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.BuyerService;

public class BuyerServiceImpl implements BuyerService{

	@Override
	public List<Item> itemsByCategory(List<Item> items, int categoryId) {
		final List<Item> itemsByCategory = new ArrayList<Item>();
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getItemCategoryId() == categoryId) {
				itemsByCategory.add(items.get(i));
			}
		}
		return itemsByCategory;
	}

	@Override
	public Category getCategoryById(List<Category> categories, int categoryId) {
		Category chosenCategory = new Category();
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).getCategoryId() == categoryId) {
				chosenCategory = categories.get(i);
			}
		}
		
		return chosenCategory;
	}

	@Override
	public boolean checkItem(List<Cart> carts, String name) {
		boolean result =false;
		for(int i =0;i<carts.size();i++) {
			if(carts.get(i).getItemName().equals(name)){
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public int takeField(List<Cart> carts, String name) {
		int takeField =0;
		for(int i=0;i<carts.size();i++) {
			if(carts.get(i).getItemName().equals(name)) {
				takeField=i;
			}
		}
		
		return takeField;
	}


	
	
}
