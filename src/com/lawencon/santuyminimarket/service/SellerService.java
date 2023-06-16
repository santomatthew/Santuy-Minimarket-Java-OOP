package com.lawencon.santuyminimarket.service;

import java.util.List;

import com.lawencon.santuyminimarket.model.Category;
import com.lawencon.santuyminimarket.model.Item;

public interface SellerService {

	boolean checkCategoryIfExist(List<Category>categories, String newCategoryName);
	boolean checkItemCategory(List<Item> items,String categoryName); 
	List<Item> getItemByCategoryName (String categoryName,List<Item>items);
	int removeItemByName (List<Item>items, String name);
	
}
