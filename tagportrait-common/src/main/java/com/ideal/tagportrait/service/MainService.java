package com.ideal.tagportrait.service;

import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.dto.Series;
import com.ideal.tagportrait.dto.TreeNode;
import com.ideal.tagportrait.dto.XAxis;
import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Transactional
@Service
public class MainService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TagRepository tagRepository;

    public List<TreeNode> getTagTree(String id, Long areaId) {
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        if (StringUtils.isBlank(id)) {
            id = "0";
        }
        logger.info(String.format("id:%s", id));
        List list = tagRepository.getTagTreeByIdAndArea(id, areaId);
        for (int i=0; i<list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);

            String tagId = objects[0].toString();
            String name = objects[1].toString() + "(" + objects[2].toString() + ")";
            boolean leafFlag = Boolean.parseBoolean(objects[3].toString());
            //logger.debug("leafFlag:" + leafFlag);
            TreeNode treeNode = new TreeNode(tagId, id, name, !leafFlag);
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }


    public BarChart getChildrenTagData(String tagId, Long areaId) {
        BarChart barChart = new BarChart();
        logger.debug("tagId:" + tagId);
        List list = tagRepository.getChildrenTagData(tagId, areaId);
        List<XAxis> xAxisList = new ArrayList<XAxis>();
        XAxis xAxis = new XAxis("category");
        List<String> xAxisData = new ArrayList<String>();

        List<Series> seriesList = new ArrayList<Series>();
        Series series = new Series("num", "bar");
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

    public Tag getTag(String tagId) {
        return tagRepository.findOne(Long.parseLong(tagId));
    }

    public void saveTagDescription(String tagId, String description) {
        Tag tag = tagRepository.findOne(Long.parseLong(tagId));
        tag.setDescription(description);
        tagRepository.save(tag);
    }
}
