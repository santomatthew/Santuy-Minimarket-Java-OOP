package com.lawencon.santuyminimarket.service.impl;

import java.util.List;

import com.lawencon.santuyminimarket.model.Category;
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

}
