package com.example.plantmall.service;

import java.util.List;

import com.example.plantmall.domain.FundingOrder;

public interface FundingRelationService {
	
	List<FundingOrder> getAllFundingOrderList(String id);
	FundingOrder getFundingOrder(String id);
	
	void insertFundingOrder(FundingOrder fundingOrder);
	void deleteFundingOrder(String id);

}