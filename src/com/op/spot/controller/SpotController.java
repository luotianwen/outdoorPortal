package com.op.spot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.entity.district.Citys;
import com.op.entity.district.Countys;
import com.op.plugin.page.Page;
import com.op.service.district.CitysService;
import com.op.service.district.ProvincesService;
import com.op.spot.entity.Spot;
import com.op.spot.service.*;
import com.op.solr.SolrPage;
import com.op.solr.util.SpotSearchService;
import com.op.spot.dto.SpotResult;
import com.op.spot.dto.SpotSearchParameter;
import com.op.util.Const;
import com.op.util.Distance;
import com.op.util.HTMLEscape;
import com.op.util.StringUtil;
import com.op.util.jedis.RedisUtil;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 景点
 */
@Controller
@RequestMapping(value = "/spot")
public class SpotController extends BaseController {

    /**
     * 游玩主题
     */
    @Resource(name = "playthemeServiceImpl")
    private PlaythemeService playthemeServiceImpl;

    /**
     * 景点级别
     */
    @Resource(name = "spotlevelServiceImpl")
    private SpotlevelService spotlevelServiceImpl;

    /**
     * 适合人群
     */
    @Resource(name = "suitablecrowdServiceImpl")
    private SuitablecrowdService suitablecrowdServiceImpl;


    /**
     * 景区
     */
    @Resource(name = "spotServiceImpl")
    private SpotService spotServiceImpl;

    // servlet 上下文
    String contextPath = null;

    // 模板文件名
    String ftl = null;



	
	/**
	 * 景点聚合
	 * @param page
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	/**
	 * 门票价格
	 */
	@Resource(name="ticketpriceServiceImpl")
	private TicketpriceService ticketpriceServiceImpl;
	 
	/**
	 * 省份
	 */
	@Resource(name="provincesServiceImpl")
	private ProvincesService provincesServiceImpl;
	
	/**
	 * 市区
	 */
	@Resource(name="citysServiceImpl")
	private CitysService citysServiceImpl;
	
	/**
	 * 景点搜索
	 */
	@Resource(name="spotSearchService")
	private SpotSearchService spotSearchService;
    /**
     * 景点门票产品
     */
    @Resource(name="productServiceImpl")
    private ProductService productServiceImpl;
	/**
	 * 景点聚合
	 * @param page
	 * @param mv
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/list")
    public ModelAndView getScenicspotintroductionList(Page page, ModelAndView mv,String pt) {

        try {
            mv.addObject("playtheme", playthemeServiceImpl.playthemeByPidList());
            mv.addObject("spotlevel", spotlevelServiceImpl.spotlevelList());
            mv.addObject("suitablecrowd", suitablecrowdServiceImpl.suitablecrowdList());
            mv.addObject("ticketprice", ticketpriceServiceImpl.ticketpriceList());
            mv.addObject("provinces",provincesServiceImpl.selectProvinces());
            
            setHeaderMap(mv,"景区");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mv.setViewName("spot/scenic-list");
        return mv;
    }
    /**
     * 景点产品
     * @param spotid
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getProductBySpotId")
    public ModelAndView getProductBySpotId( ModelAndView mv,int spotid) {

        try {
            mv.addObject("products", productServiceImpl.getProductsById(spotid));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mv.setViewName("spot/spot-product");
        return mv;
    }

    /**
	 * 根据省份ID查询城市
	 */
	@RequestMapping("/citys")
	@ResponseBody
	public Map<String,Object> citys(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<Citys> citys = citysServiceImpl.selectCitys(id);
			map.put("citys", citys);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			logger.error("省市联查方法异常！！！！");
			map.put(Const.RESPONSE_STATE, 500);
		}
		
		return map;
	} 
	
	/**
	 * 根据城市ID查询区县
	 */
	@RequestMapping("/countys")
	@ResponseBody
	public Map<String,Object> countys(String id){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<Countys> countys = citysServiceImpl.selectCounty(id);
			map.put("countys", countys);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			logger.error("省市联查方法异常！！！！");
			map.put(Const.RESPONSE_STATE, 500);
		}
		
		return map;
	} 
    
    @RequestMapping("/nowCity")
	@ResponseBody
	public Map<String,String> nowCity(String name){
    	Map<String,String> map = new HashMap<String,String>();
    	
    	map.put(Const.RESPONSE_STATE, "500");
    	try {
			citysServiceImpl.selectNowCity(map,name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return map;
    }
	
    /**
     * 景点详情
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xiang/{id}")
    public ModelAndView getScenicspotintroductionList(@PathVariable(value = "id") int id, ModelAndView mv, HttpServletRequest request) throws Exception {
        try {
            String newUrl = "/view/spot/show/" + id + ".html";
            // servlet 上下文
            contextPath = request.getServletContext().getRealPath("/");
            // 模板文件名
            ftl = "static/ftl/spot/spot-show.html";
            mv.setViewName("not");
            //查询景区存不存在
            Spot spot = spotServiceImpl.findById(id);
            //景区存在并且状态是发布的
            if (null != spot && spot.getStatus() == 1) {
                //查看缓存里面的景区
                String key = String.format(Const.SPOT_ID, id);
                String value = RedisUtil.get(key);
                // 创建该文件对象判断是否存在
                File newFile = new File(contextPath + newUrl);
                //缓存中没有或者过期
                if (StringUtil.isEmpty(value)) {
                    RedisUtil.set(key, "1");
                    RedisUtil.expire(key, Const.SPOT_ID_UPDATE_TIME);
                    //存在文件就删除 更新新的
                    if (newFile.isFile() && newFile.exists()) {
                        newFile.delete();
                    }
                    newFile = new File(contextPath + newUrl);
                }

                if (newFile.exists()) {
                    mv.setViewName("spot/show/" + id);
                } else {
                    try {
                        //生成详情静态页面
                        spotServiceImpl.autoDetailById(id, ftl, newUrl, contextPath);
                    } catch (Exception e) {
                    }
                    if (newFile.exists()) {
                        mv.setViewName("spot/show/" + id);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("景点展示页异常！！！！！！！", e);

        }

        return mv;

    }
    /**
     * 景点介绍详情
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/introduction/{id}")
    public ModelAndView getintroduction(@PathVariable(value = "id") int id, ModelAndView mv, HttpServletRequest request) throws Exception {
        try {
            String newUrl = "/view/spot/show/introduction-" + id + ".html";
            // servlet 上下文
            contextPath = request.getServletContext().getRealPath("/");
            // 模板文件名
            ftl = "static/ftl/spot/spot-introduction-show.html";
            mv.setViewName("not");
            //查询景区存不存在
            Spot spot = spotServiceImpl.findById(id);
            //景区存在并且状态是发布的
            if (null != spot && spot.getStatus() == 1) {
                //查看缓存里面的景区
                String key = String.format(Const.SPOT_ID, id);
                String value = RedisUtil.get(key);
                // 创建该文件对象判断是否存在
                File newFile = new File(contextPath + newUrl);
                //缓存中没有或者过期
                if (StringUtil.isEmpty(value)) {
                    RedisUtil.set(key, "1");
                    RedisUtil.expire(key, Const.SPOT_ID_UPDATE_TIME);
                    //存在文件就删除 更新新的
                    if (newFile.isFile() && newFile.exists()) {
                        newFile.delete();
                    }
                    newFile = new File(contextPath + newUrl);
                }

                if (newFile.exists()) {
                    mv.setViewName("spot/show/introduction-" + id);
                } else {
                    try {
                        //生成详情静态页面
                        spotServiceImpl.autointroductionDetailById(id, ftl, newUrl, contextPath);
                    } catch (Exception e) {
                    }
                    if (newFile.exists()) {
                        mv.setViewName("spot/show/introduction-" + id);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("景点展示页异常！！！！！！！", e);

        }

        return mv;
    }


    @RequestMapping(value = "search")
	@ResponseBody
    public Object query(SolrPage page,SpotSearchParameter sp) throws Exception {
		// 封装查询半径(移动地图异步查询)
		if(!StringUtils.isEmpty(sp.getBl()) && !StringUtils.isEmpty(sp.getNorthEast())){
			mapMoveendSet(sp);
		}
		
		spotSearchService.querySpot(sp, page);
		
		List<SpotResult> spotList = (List<SpotResult>) page.getResult();
		
		// 定义HTML标签的正则表达式
//		String regEx_html = "<[^>]+>";
//        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		
		for(int i=0,len=spotList.size();i<len;i++){
			//去除内容内所有html标签
			if(!StringUtils.isEmpty(spotList.get(i).getScenicspotintroduction())){
				//spotList.get(i).setScenicspotintroduction(p_html.matcher(spotList.get(i).getScenicspotintroduction().trim()).replaceAll("").replaceAll("&nbsp;", ""));
				spotList.get(i).setScenicspotintroduction(HTMLEscape.htmlEscape(spotList.get(i).getScenicspotintroduction().trim()));
			}
		}
		
		return page;
    }

    /**
	 * 方法描述：封装查询半径（移动地图异步查询）
	 * 返回类型：void
	 * @param sp
	 */
	public void mapMoveendSet(SpotSearchParameter sp){
		String[] bl = sp.getBl().split(",");// 中心坐标
		String[] northEast = sp.getNorthEast().split(",");// 东北角坐标
		double latCenterRad = Double.parseDouble(bl[0]);// 中心点纬度
		double lonCenterRad = Double.parseDouble(bl[1]);// 中心点经度
		double latVals = Double.parseDouble(northEast[1]);// 半径外纬度
		double lonVals = Double.parseDouble(northEast[0]);// 半径外经度
		sp.setD(Distance.doubleVal(latCenterRad, lonCenterRad, latVals, lonVals));// 查询半径（单位：公里）
		sp.setBl(bl[0]+","+bl[1]);// 重置中心点坐标（纬度在前，精度在后）
	}
    
    
    /**
     * 特色看点详情
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/characteristicaspectslist/{id}")
    public ModelAndView getcharacteristicaspects(@PathVariable(value = "id") int id, ModelAndView mv, HttpServletRequest request) throws Exception {
        try {
            String newUrl = "/view/spot/show/characteristicaspects-" + id + ".html";
            // servlet 上下文
            contextPath = request.getServletContext().getRealPath("/");
            // 模板文件名
            ftl = "static/ftl/spot/spot-characteristicaspects-show.html";
            mv.setViewName("not");
            //查询景区存不存在
            Spot spot = spotServiceImpl.findById(id);
            //景区存在并且状态是发布的
            if (null != spot && spot.getStatus() == 1) {
                //查看缓存里面的景区
                String key = String.format(Const.SPOT_ID, id);
                String value = RedisUtil.get(key);
                // 创建该文件对象判断是否存在
                File newFile = new File(contextPath + newUrl);
                //缓存中没有或者过期
                if (StringUtil.isEmpty(value)) {
                    RedisUtil.set(key, "1");
                    RedisUtil.expire(key, Const.SPOT_ID_UPDATE_TIME);
                    //存在文件就删除 更新新的
                    if (newFile.isFile() && newFile.exists()) {
                        newFile.delete();
                    }
                    newFile = new File(contextPath + newUrl);
                }

                if (newFile.exists()) {
                    mv.setViewName("spot/show/characteristicaspects-" + id);
                } else {
                    try {
                        //特色看点详情
                        spotServiceImpl.autocharacteristicaspectsDetailById(id, ftl, newUrl, contextPath);
                    } catch (Exception e) {
                    }
                    if (newFile.exists()) {
                        mv.setViewName("spot/show/characteristicaspects-" + id);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("景点展示页异常！！！！！！！", e);

        }

        return mv;

    }
    /**
     * 精彩攻略详情
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wonderfulstrategys/{id}")
    public ModelAndView getwonderfulstrategys(@PathVariable(value = "id") int id, ModelAndView mv, HttpServletRequest request) throws Exception {
        try {
            String newUrl = "/view/spot/show/wonderfulstrategys-" + id + ".html";
            // servlet 上下文
            contextPath = request.getServletContext().getRealPath("/");
            // 模板文件名
            ftl = "static/ftl/spot/spot-wonderfulstrategys-show.html";
            mv.setViewName("not");

                //查看缓存里面的景区
                String key = String.format(Const.SPOT_ID, id);
                String value = RedisUtil.get(key);
                // 创建该文件对象判断是否存在
                File newFile = new File(contextPath + newUrl);
                //缓存中没有或者过期
                if (StringUtil.isEmpty(value)) {
                    RedisUtil.set(key, "1");
                    RedisUtil.expire(key, Const.SPOT_ID_UPDATE_TIME);
                    //存在文件就删除 更新新的
                    if (newFile.isFile() && newFile.exists()) {
                        newFile.delete();
                    }
                    newFile = new File(contextPath + newUrl);
                }

                if (newFile.exists()) {
                    mv.setViewName("spot/show/wonderfulstrategys-" + id);
                } else {
                    try {
                        //生成详情静态页面
                        spotServiceImpl.autowonderfulstrategysDetailById(id, ftl, newUrl, contextPath);
                    } catch (Exception e) {
                    }
                    if (newFile.exists()) {
                        mv.setViewName("spot/show/wonderfulstrategys-" + id);
                    }
                }

        } catch (Exception e) {
            logger.error("景点展示页异常！！！！！！！", e);

        }

        return mv;

    }
    /**
     * 精彩攻略详情
     *
     * @param id
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wonderfulstrategyslist/{id}")
    public ModelAndView getwonderfulstrategyslist(@PathVariable(value = "id") int id, ModelAndView mv, HttpServletRequest request) throws Exception {
        try {
            String newUrl = "/view/spot/show/wonderfulstrategyslist-" + id + ".html";
            // servlet 上下文
            contextPath = request.getServletContext().getRealPath("/");
            // 模板文件名
            ftl = "static/ftl/spot/spot-wonderfulstrategyslist-show.html";
            mv.setViewName("not");
            //查询景区存不存在
            Spot spot = spotServiceImpl.findById(id);
            //景区存在并且状态是发布的
            if (null != spot && spot.getStatus() == 1) {
                //查看缓存里面的景区
                String key = String.format(Const.SPOT_ID, id);
                String value = RedisUtil.get(key);
                // 创建该文件对象判断是否存在
                File newFile = new File(contextPath + newUrl);
                //缓存中没有或者过期
                if (StringUtil.isEmpty(value)) {
                    RedisUtil.set(key, "1");
                    RedisUtil.expire(key, Const.SPOT_ID_UPDATE_TIME);
                    //存在文件就删除 更新新的
                    if (newFile.isFile() && newFile.exists()) {
                        newFile.delete();
                    }
                    newFile = new File(contextPath + newUrl);
                }

                if (newFile.exists()) {
                    mv.setViewName("spot/show/wonderfulstrategyslist-" + id);
                } else {
                    try {
                        //生成详情静态页面
                        spotServiceImpl.autowonderfulstrategyslistDetailById(id, ftl, newUrl, contextPath);
                    } catch (Exception e) {
                    }
                    if (newFile.exists()) {
                        mv.setViewName("spot/show/wonderfulstrategyslist-" + id);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("景点展示页异常！！！！！！！", e);

        }

        return mv;

    }
    
    /**
	 * 景点搜索页面查看大图
	 */
	@RequestMapping("/bigMap")
	public ModelAndView bigMap(ModelAndView mv,String center){
		mv.addObject("center", center);
		mv.setViewName("spot/bigMap");
		
		return mv;
	}
    
}
