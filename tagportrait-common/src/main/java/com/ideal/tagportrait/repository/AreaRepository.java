package com.ideal.tagportrait.repository;

import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public interface AreaRepository extends JpaRepository<Area, Long> {
    public List<Area> findAll();
}
