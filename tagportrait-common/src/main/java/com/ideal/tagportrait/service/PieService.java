package com.ideal.tagportrait.service;

import com.ideal.tagportrait.dto.PieData;
import com.ideal.tagportrait.dto.PieChart;
import com.ideal.tagportrait.dto.SeriesPie;
import com.ideal.tagportrait.repository.AnalysisRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 17:31
 */
@Transactional
@Service
public class PieService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AnalysisRepository analysisRepository;

    public List<PieChart> findAll() {
        return null;
    }

    public List getTagData(String tagName) {
        List list = analysisRepository.getAnalysisTagNumByTagName(tagName);
//        PieChart pieChart = new PieChart();
//        List<SeriesPie> pieSeriesList = new ArrayList<SeriesPie>();
//        SeriesPie pieSeries = null;
//        List<PieData> pieDataList = new ArrayList<PieData>();
//        for (int i = 0; i < list.size(); i++) {
//            String[] paramObj = (String[]) list.get(i);
////            String areaName = objects[0].toString();
////            String area = "全国";
//            PieData pieData = new PieData( Long.valueOf(paramObj[1]),paramObj[0]);
//            pieDataList.add(pieData);
//        }
//        pieSeries.setData(pieDataList);
//        pieSeriesList.add(pieSeries);
//        pieChart.setSeries(pieSeriesList);
        return list;
    }
}
