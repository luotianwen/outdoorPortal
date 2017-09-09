package com.op.spot.service;

import com.op.spot.entity.Characteristicaspect;
import com.op.spot.entity.Spot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spotService")
public interface SpotService {
    Spot findById(int id) throws Exception;

    Spot findDetailById(int id) throws Exception;

    void autoDetailById(int id, String ftl, String newUrl, String contextPath) throws Exception;

    //特色看点列表
    List<Characteristicaspect> getCharacteristicaspectList(int spotId) throws Exception;

    void autointroductionDetailById(int id, String ftl, String newUrl, String contextPath) throws Exception;
    //特色看点详情
    void autocharacteristicaspectsDetailById(int id, String ftl, String newUrl, String contextPath) throws Exception;
    //攻略详情
    void autowonderfulstrategysDetailById(int id, String ftl, String newUrl, String contextPath) throws Exception;
    //攻略列表
    void autowonderfulstrategyslistDetailById(int id, String ftl, String newUrl, String contextPath) throws Exception;
}
