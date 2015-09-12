package com.ideal.tagportrait.service;

import com.google.common.collect.Lists;

import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
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
    public List<Tag> findFirstTags() {
        return tagRepository.findByLevel(1L);
    }

    public List<Tag> findSecondTags(Long firstTagId) {
        return tagRepository.findByLevelAndParentId(2L, firstTagId);
    }

    public List<Tag> findThirdTags(Long secondTagId) {
        return tagRepository.findByLevelAndParentId(3L, secondTagId);
    }

}
