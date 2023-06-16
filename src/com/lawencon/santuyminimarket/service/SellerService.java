package com.lawencon.santuyminimarket.service;

import java.util.List;

import com.lawencon.santuyminimarket.model.Category;

public interface SellerService {

	boolean checkCategoryIfExist(List<Category>categories, String newCategoryName);
	
}
