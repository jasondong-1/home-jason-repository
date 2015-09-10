package com.ideal.tagportrait.common.repository;

import com.ideal.tagportrait.common.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanyuntao
 * @mail 18918567223@189.cn
 * created on 2015/09/10 10:33
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
