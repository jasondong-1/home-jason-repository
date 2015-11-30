package com.ideal.tagportrait.security.repository;


import com.ideal.tagportrait.entity.security.Role;
import com.ideal.tagportrait.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT o FROM Role o where o.id IN (?1)")
    public List<Role> findByIds(List<Long> roleIds);
}
