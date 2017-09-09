package com.op.spot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.spot.entity.Ticketprice;

@Service("ticketpriceService")
public interface TicketpriceService {
	/**
	 * 查询所有门票价格
	 * @return
	 * @throws Exception
	 */
	public List<Ticketprice> ticketpriceList() throws Exception;
	
}
