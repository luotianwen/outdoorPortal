package com.op.spot.service;

import java.util.List;

import com.op.spot.entity.Product;
/** 
 * 景点产品(s_product)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
public interface ProductService {

	/**
	 * 根据产品id获取门票信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
    Product getTicketInfo(int productId)throws Exception;

	 List<Product> getProductsById(Integer id) throws Exception;
}
