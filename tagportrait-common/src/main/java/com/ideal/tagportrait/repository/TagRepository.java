package com.ideal.tagportrait.repository;

import com.ideal.tagportrait.entity.Student;
import com.ideal.tagportrait.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT o.id,o.name,a.tag_num,o.leaf_flag FROM tb_tag o,tb_analysis a WHERE o.id = a.tag_id AND o.parent_id=?1 AND a.area_id=?2", nativeQuery = true)
    public List getTagTreeByIdAndArea(String id, Long areaId);

//    @Query(value = "SELECT o.id,o.name,a.tag_num FROM tb_tag o,tb_analysis a WHERE o.id = a.tag_id AND o.parent_id=?1 AND a.area_id=?2 ORDER BY a.tag_num ", nativeQuery = true)
//    public List getChildrenTagData(String tagId, Long areaId);

    @Query(value = "SELECT o.id,o.name,a.tag_num FROM tb_tag o,tb_analysis a WHERE o.id = a.tag_id AND o.parent_id=?1 AND a.area_id=?2 ORDER BY a.tag_num DESC LIMIT 5", nativeQuery = true)
    public List getChildrenTagDataTop(String tagId, Long areaId);
    List<Tag> findByLevel(Long level);

    List<Tag> findByLevelAndParentId(Long level, Long parentId);
}
