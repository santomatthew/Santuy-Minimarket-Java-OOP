package com.lawencon.santuyminimarket.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.Item;
import com.lawencon.santuyminimarket.service.SellerService;

public class SellerServiceImpl implements SellerService{

	@Override
	public boolean checkCategoryIfExist(List<Category> categories, String newCategoryName) {
		boolean result = false;
		for(int i=0;i<categories.size();i++) {
			if(categories.get(i).getCategoryName().equals(newCategoryName)) {
				result = true;
			}
		}
		
		return result;
	}

	@Override
	public boolean checkItemCategory(List<Item> items,String categoryName) {
		boolean result = false;
		
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getCategoryName().equals(categoryName)) {
				result=true;
			}
		}
		
		return result;
	}

	@Override
	public List<Item> getItemByCategoryName(String categoryName,List<Item> items) {
		final List<Item> newItems = new ArrayList<Item>();
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getCategoryName().equals(categoryName)) {
				newItems.add(items.get(i));
			}
		}
		return newItems;
	}

	@Override
	public int getItemFieldByName(List<Item> items, String name) {
		int takeField =0;
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getItemName().equals(name)) {
				takeField =i;
			}
		}
		
		return takeField;
		
	}

	@Override
	public List<Category> getNewCategory(List<Category> categories, String categoryName) {
		final List<Category> newCategories = new ArrayList<Category>();
		for(int i=0;i<categories.size();i++) {
			if(!categories.get(i).getCategoryName().equals(categoryName)) {
				newCategories.add(categories.get(i));
			}
		}
		return newCategories;
	}

}
