package com.example.plantmall.controller;

import java.util.List;
import java.util.ArrayList;
//import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.plantmall.service.AuthService;
import com.example.plantmall.service.EnquiryService;
import com.example.plantmall.service.ProductService;
import com.example.plantmall.service.ReviewService;
import com.example.plantmall.domain.Enquiry;
import com.example.plantmall.domain.Product;
import com.example.plantmall.domain.Review;

@Controller
public class ViewProductController {
	
	private ProductService productService;
	@Autowired
	private AuthService authService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private EnquiryService enquiryService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//제품 목록 페이지
	@RequestMapping("/product/list")
	public String showProductList(@RequestParam(value="categoryId", required=false) String categoryId, 
								ModelMap model) throws Exception {
		List<Product> itemList;
		String categoryName;
		
		//모든 제품 목록 보기
		if (categoryId == null) {
			itemList = this.productService.getAllProduct();
			categoryName = "전체"; 
		}
		//카테고리별 제품 목록 보기
		else {
			itemList = this.productService.getProductListByCategory(categoryId);
			categoryName = this.productService.getCategory(categoryId).getC_name();
			/*
			if (categoryName.equals("flower"))
				categoryName = "꽃";
			else if (categoryName.equals("wildflower"))
				categoryName = "야생화";
			else if (categoryName.equals("herb"))
				categoryName = "허브";
			else if (categoryName.equals("tree"))
				categoryName = "나무";
			else if (categoryName.equals("succulentPlant"))
				categoryName = "다육";
			else if (categoryName.equals("marineAlgae"))
				categoryName = "해조";
			else if (categoryName.equals("gardeningSupplies"))
				categoryName = "원예용품";
				*/
			
		}
		List<List<Product>> productList = new ArrayList<>();
		List<List<String>> userNameList = new ArrayList<>();

		try {
			for (int i = 0; i < itemList.size(); i += 4) {
				List<Product> miniProductList = new ArrayList<>();
				List<String> miniUserNameList = new ArrayList<>();
				 
				productList.add(miniProductList);
				userNameList.add(miniUserNameList);
				for (int j = 0; j < 4; j++) {
			//		String userName = this.~.getUserName(p.userId); //현재 user명 가져오는 방법 없음..
					String userName = "admin";	//임시 userName
					miniProductList.add(itemList.get(i+j));
					miniUserNameList.add(userName);
				} 
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("더 이상 제품을 찾을 수 없음");
		}
		System.out.println(categoryName+" 카테고리 제품 model -> view 전달");

		model.put("categoryName", categoryName);
		model.put("productList", productList);
		model.put("userNameList", userNameList);
		
		return "product/productList";		
	}	
	
	//제품 상세 페이지
	@RequestMapping("/product/detail")
	public String showProductDetail(@RequestParam(value="productId", required=false) String productId, 
			ModelMap model) throws Exception {
		Product product = this.productService.getProduct(productId);
		//String userName = this.~.getUserName(p.userId);	//예상 사용자명 가져오기 코드
		String seller = "admin";	//임시 userName
		//Date orderDate = this.~.getOrderDate(p.userId);	//예상 주문일자 가져오기 코드
		String orderDate = "5월 27일 (금)"; 	//임시 orderDate 
		
		// productDetail에서 보일 리뷰 리스트
		List<Review> list = reviewService.getReviewsByProductId(productId);
		if (list != null) {
			product.setReviews(list);
			for (int i = 0; i < list.size(); i++) {
				String reviewUserName = authService.getUserById(list.get(i).getUserId()).getUserName();
				product.getReviews().get(i).setUserName(reviewUserName);
			}
		}
		
		// productDetail에서 보일 문의 리스트
		List<Enquiry> enquiryList = enquiryService.getEnquiryListByProductId(productId);
		if (enquiryList != null) {
			product.setEnquiryList(enquiryList);
			for (int i = 0; i < enquiryList.size(); i++) {
				String enquiryUserName = authService.getUserById(enquiryList.get(i).getUserId()).getUserName();
				product.getEnquiryList().get(i).setUserName(enquiryUserName);
			}
		}

		model.put("product",  product);
		model.put("seller", seller);
		model.put("orderDate", orderDate);
		model.addAttribute("reviewList", product.getReviews());
		model.addAttribute("enquiryList", product.getEnquiryList());
		
		System.out.println(product.getP_name()+" 제품 상세 model -> view 전달");
		
		return "product/productDetail";
	}
	
}
