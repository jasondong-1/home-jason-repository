package com.ideal.tagportrait.web.service.main;

import com.google.common.collect.Lists;
import com.ideal.tagportrait.common.entity.Student;
import com.ideal.tagportrait.common.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyuntao
 * @mail 18918567223@189.cn
 * created on 2015/09/10 10:28
 */
@Transactional
@Service
public class MainService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StudentRepository studentRepository;

    public List<String> findStudentNames() {
        List<Student> students = studentRepository.findAll();
        List<String> names = Lists.newArrayList();
        for(Student student : students) {
            names.add(student.getName());
        }
        return names;
    }
}
