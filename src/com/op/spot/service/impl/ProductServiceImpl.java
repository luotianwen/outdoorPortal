package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.spot.entity.Product;
import com.op.spot.service.ProductService;

/** 
 * 景点产品(s_product)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据产品id获取门票信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
    public Product getTicketInfo(int productId)throws Exception {
		return (Product)dao.findForObject("productMapper.getTicketInfo", productId);
	}

	/**
	 * 根据景点id获取产品信息
	 * @param spotId
	 * @return
	 * @throws Exception
	 */
	public List<Product> getProductsById(Integer spotId) throws Exception {
		return (List<Product>) dao.findForList("productMapper.getproductListBySpotId", spotId);
	}

}
