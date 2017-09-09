package com.op.spot.service.impl;

import com.op.dao.BaseDao;
import com.op.dto.travels.TravelsSearchDTO;
import com.op.solr.SolrPage;
import com.op.solr.util.TravelsSearchService;
import com.op.spot.entity.*;
import com.op.spot.service.PlaythemeService;
import com.op.spot.service.SpotService;
import com.op.util.FreeMarkerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("spotServiceImpl")
public class SpotServiceImpl implements SpotService {

    @Resource(name = "baseDaoImpl")
    private BaseDao dao;

    @Resource(name = "freeMarkerUtil")
    FreeMarkerUtil freeMarkerUtil;
    /**
     * 游玩主题
     */
    @Resource(name = "playthemeServiceImpl")
    private PlaythemeService playthemeServiceImpl;
    /**
     * 游记搜索
     */
    @Resource(name="travelsSearchService")
    TravelsSearchService travelsSearchService;

    @Override
    public Spot findById(int id) throws Exception {
        return (Spot) dao.findForObject("spotMapper.getSpotById", id);
    }

    public Spot findDetailById(int id) throws Exception {
        return (Spot) dao.findForObject("spotMapper.getSpotDetailById", id);
    }

    //查询特色列表
    public List<Characteristicaspect> getCharacteristicaspectList(int spotId) throws Exception {
        return (List<Characteristicaspect>) dao.findForList("characteristicaspectMapper.getCharacteristicaspectList", spotId);
    }

    //查询详情里的特色列表
    public List<Characteristicaspect> getCharacteristicaspectTop3List(int spotId) throws Exception {
        return (List<Characteristicaspect>) dao.findForList("characteristicaspectMapper.getCharacteristicaspectTop3List", spotId);
    }

    //查询特色看点
    public Characteristicaspect findCharacteristicaspectById(String id) throws Exception {
        return (Characteristicaspect) dao.findForObject("characteristicaspectMapper.getCharacteristicaspectById", id);
    }

    //介绍详情
    public Scenicspotintroduction getScenicspotintroductionById(int id) throws Exception {
        return (Scenicspotintroduction) dao.findForObject("scenicspotintroductionMapper.getScenicspotintroductionById", id);
    }


    //查询出行通过景区id
    public Traveltips findTraveltipsBySId(int id) throws Exception {
        return (Traveltips) dao.findForObject("traveltipsMapper.getTraveltipsById", id);
    }

    //查询详情里的特色列表
    public List<Attractionsfacilities> getAttractionsfacilitiesList() throws Exception {
        return (List<Attractionsfacilities>) dao.findForList("attractionsfacilitiesMapper.getAttractionsfacilitiesList");
    }

    //门票信息
    public Ticketinformation getTicketinformationById(int id) throws Exception {
        return (Ticketinformation) dao.findForObject("ticketinformationMapper.getTicketinformationById", id);
    }

    //景点照片
    public List<Attractionsphotos> getAttractionsphotosList(int id) throws Exception {
        return (List<Attractionsphotos>) dao.findForList("attractionsphotosMapper.getAttractionsphotosList", id);
    }

    //精彩攻略
    public List<Wonderfulstrategy> getWonderfulstrategyIsfirstList(int id) throws Exception {
        return (List<Wonderfulstrategy>) dao.findForList("wonderfulstrategyMapper.getWonderfulstrategyIsfirstList", id);
    }

    //精彩攻略
    public Wonderfulstrategy getWonderfulstrategyById(int id) throws Exception {
        return (Wonderfulstrategy) dao.findForObject("wonderfulstrategyMapper.getWonderfulstrategyById", id);
    }

    private List<Wonderfulstrategy> getWonderfulstrategyList(int id) throws Exception {
        return (List<Wonderfulstrategy>) dao.findForList("wonderfulstrategyMapper.getWonderfulstrategyList", id);
    }

    //交通情况
    public Trafficguide getTrafficguideById(int id) throws Exception {
        return (Trafficguide) dao.findForObject("trafficguideMapper.getTrafficguideById", id);
    }

    //介绍详情
    @Override
    public void autointroductionDetailById(int id, String ftl, String targetFile, String contextPath) throws Exception {
        Spot spot = this.findDetailById(id);
        //介绍详情
        Scenicspotintroduction scenicspotintroduction = getScenicspotintroductionById(spot.getId());
        // 填充数据
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("spot", spot);
        data.put("scenicspotintroduction", scenicspotintroduction);
        setHeader(data);
        freeMarkerUtil.createFile(ftl, data, targetFile, contextPath);

    }

    //特色看点
    @Override
    public void autocharacteristicaspectsDetailById(int id, String ftl, String targetFile, String contextPath) throws Exception {
        // Characteristicaspect characteristicaspect = this.findCharacteristicaspectById(id+"");
        List<Characteristicaspect> characteristicaspects = this.getCharacteristicaspectList(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("characteristicaspects", characteristicaspects);
        setHeader(data);
        freeMarkerUtil.createFile(ftl, data, targetFile, contextPath);
    }
    //设置头部标签
    private void setHeader(Map data){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ht", "景区");
        data.put("map", map);
    }
    //精彩攻略详情
    @Override
    public void autowonderfulstrategysDetailById(int id, String ftl, String targetFile, String contextPath) throws Exception {
        Wonderfulstrategy wonderfulstrategy = this.getWonderfulstrategyById(id);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("wonderfulstrategy", wonderfulstrategy);
        setHeader(data);
        freeMarkerUtil.createFile(ftl, data, targetFile, contextPath);
    }
    //精彩攻略列表
    @Override
    public void autowonderfulstrategyslistDetailById(int id, String ftl, String targetFile, String contextPath) throws Exception {
        //精彩攻略
        List<Wonderfulstrategy> wonderfulstrategys = getWonderfulstrategyList(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("wonderfulstrategys", wonderfulstrategys);
       setHeader(data);
        freeMarkerUtil.createFile(ftl, data, targetFile, contextPath);
    }

   //景区详情
    public void autoDetailById(int id, String ftl, String targetFile, String contextPath) throws Exception {
        Spot spot = this.findDetailById(id);
        //游玩主题
        List<Playtheme> playthemes = playthemeServiceImpl.playthemeList();
        //景点设施
        List<Attractionsfacilities> attractionsfacilities = getAttractionsfacilitiesList();
        
        String labelss = "";
        String labelssnum[];
        if (!StringUtils.isEmpty(spot.getLabelss())) {
            labelssnum = spot.getLabelss().split(",");
            for (int i = 0; i < labelssnum.length; i++) {
                for (Playtheme playtheme : playthemes
                        ) {
                    if ((playtheme.getId() + "").equals(labelssnum[i])) {
                        labelss += playtheme.getName() + ",";
                    }
                }
            }
            spot.setLabelss(labelss);
        }

        //特色看点
        List<Characteristicaspect> characteristicaspects = this.getCharacteristicaspectTop3List(spot.getId());
        //出行小贴
        Traveltips traveltips = findTraveltipsBySId(spot.getId());
        //门票信息
        Ticketinformation ticketinformation = getTicketinformationById(spot.getId());
        //景点照片
        List<Attractionsphotos> attractionsphotos = getAttractionsphotosList(spot.getId());
        //景点介绍
        Scenicspotintroduction scenicspotintroduction = getScenicspotintroductionById(spot.getId());

        //精彩攻略
        List<Wonderfulstrategy> wonderfulstrategys = getWonderfulstrategyIsfirstList(spot.getId());

        //交通情况
        Trafficguide trafficguide = getTrafficguideById(spot.getId());

        // 填充数据
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("spot", spot);
        data.put("characteristicaspects", characteristicaspects);
       setHeader(data);
        if (null != traveltips && !StringUtils.isEmpty(traveltips.getAttractionsfacilities())) {
            labelss = "";
            labelssnum = traveltips.getAttractionsfacilities().split(",");
            for (int i = 0; i < labelssnum.length; i++) {
                for (Attractionsfacilities playtheme : attractionsfacilities
                        ) {
                    if ((playtheme.getId() + "").equals(labelssnum[i])) {
                        labelss += playtheme.getName() + ",";
                    }
                }
            }
            traveltips.setAttractionsfacilities(labelss);
        }
        data.put("traveltips", traveltips);

        data.put("ticketinformation", ticketinformation);
        data.put("attractionsphotos", attractionsphotos);
        data.put("scenicspotintroduction", scenicspotintroduction);
        data.put("wonderfulstrategys", wonderfulstrategys);
        data.put("trafficguide", trafficguide);


        //游记
       
    	TravelsSearchDTO travelsSearchDTO=new TravelsSearchDTO();
        travelsSearchDTO.setKeyword(spot.getName());
        SolrPage page=new SolrPage();
        try {
        	travelsSearchService.getTravelsSearchResults(travelsSearchDTO, page);
        	data.put("travelsList", page.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			data.put("travelsList", "");
		}

        freeMarkerUtil.createFile(ftl, data, targetFile, contextPath);
    }



}
