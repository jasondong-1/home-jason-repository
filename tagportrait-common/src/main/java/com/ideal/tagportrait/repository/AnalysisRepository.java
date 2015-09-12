package com.ideal.tagportrait.repository;

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
public interface AnalysisRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT a.name as cityName,b.name as tagName,o.tag_num,o.percent FROM tb_analysis o,tb_area a,tb_tag b WHERE o.area_id=a.id AND o.tag_id=b.id AND tag_id=?1", nativeQuery = true)
    public List getAnalysisDataByTagId(Long tagId);
}
