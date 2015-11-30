package com.ideal.tagportrait.service;

import com.ideal.tagportrait.dto.MapChart;
import com.ideal.tagportrait.dto.MapData;
import com.ideal.tagportrait.dto.MapSeries;
import com.ideal.tagportrait.repository.AnalysisRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Transactional
@Service
public class DistService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AnalysisRepository analysisRepository;

    public MapChart getAnalysisDataByTagId(Long tagId) {
        List list = analysisRepository.getAnalysisDataByTagId(tagId);
        MapChart mapChart = new MapChart();
        List<MapSeries> mapSeriesList = new ArrayList<MapSeries>();
        MapSeries mapSeries = null;
        List<MapData> mapDataList = new ArrayList<MapData>();
        for (int i=0; i<list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            String areaName = objects[0].toString();
            String area = "全国";
            if (!StringUtils.equals(area, areaName)) {
                MapData mapData = new MapData(areaName, Long.valueOf(objects[2].toString()), Long.valueOf(objects[3].toString()));
                mapDataList.add(mapData);
            } else {
                String tagName = objects[1].toString();
                mapSeries = new MapSeries("标签 " + tagName + " 数量 " + Long.valueOf(objects[2].toString()), "map", "china");
                //
            }
        }
        mapSeries.setData(mapDataList);
        mapSeriesList.add(mapSeries);
        mapChart.setSeries(mapSeriesList);
        return mapChart;
    }

    private String convert(String text) {
        try {
            return new String(text.getBytes("GBK"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
