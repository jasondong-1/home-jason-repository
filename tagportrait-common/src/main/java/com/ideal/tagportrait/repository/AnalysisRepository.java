package com.ideal.tagportrait.repository;

import com.ideal.tagportrait.entity.Analysis;
import com.ideal.tagportrait.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    @Query(value = "SELECT a.name as cityName,b.name as tagName,o.tag_num,o.percent FROM tb_analysis o,tb_area a,tb_tag b WHERE o.area_id=a.id AND o.tag_id=b.id AND tag_id=?1", nativeQuery = true)
    public List getAnalysisDataByTagId(Long tagId);
    @Query(value = "SELECT a.id,a.name,o.heat_value FROM tb_analysis o,tb_area a,tb_tag b WHERE o.area_id=a.id AND o.tag_id=b.id AND tag_id=?1 AND a.id IN (?2) ORDER BY o.heat_value DESC LIMIT 5 ", nativeQuery = true)
    public List getAnalysisHeatValueTopByTagIdAndCity(Long tagId,List city);
    @Query(value = "SELECT a.id,a.name,o.heat_value FROM tb_analysis o,tb_area a,tb_tag b WHERE o.area_id=a.id AND o.tag_id=b.id AND tag_id=?1 AND a.id IN (?2) ORDER BY o.heat_value DESC ", nativeQuery = true)
    public List getAnalysisHeatValueByTagIdAndCity(Long tagId,List city);
    @Query(value = "SELECT b.tag_num,a.name FROM tb_tag a,tb_analysis b,tb_tag c \n" +
            "WHERE a.level = '2' AND a.parent_id = c.id AND c.level = '1' AND c.name =?1\n" +
            "AND a.id = b.tag_id AND b.area_id = ?2", nativeQuery = true)
    public List getAnalysisTagNumByTagName(String tagName,Long areaId);
    @Query(value = "SELECT a.tag_num,t.name FROM tb_analysis a,tb_tag t,tb_tag c WHERE t.id = a.tag_id AND a.tag_id IN (1,526,124,100,516,496) AND a.area_id=?1 AND t.parent_id=0 AND c.id=t.id ORDER BY t.id ", nativeQuery = true)
    public List getAnalysisFirstTagNumByTagId(Long areaId);
}
