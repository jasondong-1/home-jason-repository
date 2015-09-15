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
}
