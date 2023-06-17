package com.lawencon.santuyminimarket.service;

import java.util.List;

import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.Item;

public interface SellerService {

	boolean checkCategoryIfExist(List<Category>categories, String newCategoryName);
	boolean checkItemCategory(List<Item> items,int categoryId); 
	List<Item> getItemByCategoryId (int categoryId,List<Item>items);
	int getItemFieldByName (List<Item>items, String name);
	List<Category> getNewCategory(List<Category>categories, String categoryName);
	Category getCategoryById(List<Category> categories, int categoryId);
	boolean checkIfCategoryIdUsed(List<Category> categories, int newCategoryId);
}
