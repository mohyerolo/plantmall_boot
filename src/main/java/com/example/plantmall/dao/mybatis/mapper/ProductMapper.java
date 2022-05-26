package com.example.plantmall.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.plantmall.domain.Product;

@Mapper
public interface ProductMapper {

	Product getProduct(String productId);
	
	List<Product> getProductListByCategory(String categoryId);
	
	List<Product> searchProductList(String keywords);
	
	List<Product> showProductList(String productId);
	
	List<Product> getAllProduct();

	void insertProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(Product product);
}