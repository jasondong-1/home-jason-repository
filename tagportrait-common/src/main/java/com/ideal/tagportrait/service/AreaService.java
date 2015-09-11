package com.ideal.tagportrait.service;

import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.repository.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Transactional
@Service
public class AreaService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AreaRepository areaRepository;

    public List<Area> findAll() {
        return areaRepository.findAll();
    }
}
