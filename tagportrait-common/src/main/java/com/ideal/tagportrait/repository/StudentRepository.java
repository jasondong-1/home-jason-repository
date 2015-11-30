package com.ideal.tagportrait.repository;

import com.ideal.tagportrait.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanyuntao
 * @mail 18918567223@189.cn
 * created on 2015/09/10 10:33
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
