package com.ideal.tagportrait.service;

import com.google.common.collect.Lists;

import com.ideal.tagportrait.dto.AnalysisDto;
import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.dto.Series;
import com.ideal.tagportrait.dto.XAxis;
import com.ideal.tagportrait.entity.Analysis;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.repository.AnalysisRepository;
import com.ideal.tagportrait.repository.AreaRepository;
import com.ideal.tagportrait.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/11 10:30
 */
@Transactional
@Service
public class HotService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TagRepository tagRepository;
    @Resource
    private AnalysisRepository analysisRepository;
    public List<Tag> findFirstTags() {
        return tagRepository.findByLevel(1L);
    }
    public List<Tag> findSecondTags(Long firstTagId) {
        return tagRepository.findByLevelAndParentId(2L, firstTagId);
    }

    public List<Tag> findThirdTags(Long secondTagId) {
        return tagRepository.findByLevelAndParentId(3L, secondTagId);
    }

    public List<AnalysisDto> findHeartValueAndCity(Long id,String city) {
        List<Long> areaids = Lists.newArrayList();
        for(String s : city.split(",")) {
            areaids.add(Long.parseLong(s));
        }
        List kk = analysisRepository.getAnalysisHeatValueByTagIdAndCity(id, areaids);
        logger.warn(String.valueOf(kk.size()));
        return kk;

    }

    public BarChart getHeatValueCityTagData(Long id,String city) {
        BarChart barChart = new BarChart();
        List<Long> kk = Lists.newArrayList();
        for(String s : city.split(",")) {
            kk.add(Long.parseLong(s));
        }
        List list = analysisRepository.getAnalysisHeatValueTopByTagIdAndCity(id, kk);
        List<XAxis> xAxisList = new ArrayList<XAxis>();
        XAxis xAxis = new XAxis("category");
        List<String> xAxisData = new ArrayList<String>();

        List<Series> seriesList = new ArrayList<Series>();
        Series series = new Series("热度值", "bar");
        List<Float> seriesData = new ArrayList<Float>();
        for (int i=0; i<list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            xAxisData.add(objects[1].toString());
            seriesData.add(Float.parseFloat(objects[2].toString()));
        }
        xAxis.setData(xAxisData);
        xAxisList.add(xAxis);
        barChart.setxAxis(xAxisList);

        series.setData(seriesData);
        seriesList.add(series);
        barChart.setSeries(seriesList);
        return barChart;
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
