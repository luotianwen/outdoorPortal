package com.op.service.screening;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.op.dto.screening.ScreeningDTO;

/** 
 * 筛选条件(screening)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-08-20 13:25:21 
 */  
public interface ScreeningService {
	
    /**
     * 查询所有筛选条件
     * @return
     * @throws Exception
     */
    List<ScreeningDTO> findAllScreening(String sc_modularType,ModelAndView mv) throws Exception;
    
}
