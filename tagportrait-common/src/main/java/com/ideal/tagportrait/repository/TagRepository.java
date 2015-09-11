package com.ideal.tagportrait.repository;

import com.ideal.tagportrait.entity.Student;
import com.ideal.tagportrait.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT o.id,o.name,a.tag_num,o.leaf_flag FROM tb_tag o,tb_analysis a WHERE o.id = a.tag_id AND o.parent_id=?1 AND a.area_id=1", nativeQuery = true)
    public List getTagTreeByIdAndArea(String id);

    @Query(value = "SELECT o.id,o.name,a.tag_num FROM tb_tag o,tb_analysis a WHERE o.id = a.tag_id AND o.parent_id=?1 AND a.area_id=1", nativeQuery = true)
    public List getChildrenTagData(String tagId);
}
