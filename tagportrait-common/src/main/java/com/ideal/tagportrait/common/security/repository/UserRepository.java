package com.ideal.tagportrait.common.security.repository;

import com.ideal.tagportrait.common.entity.security.User;
import com.ideal.tagportrait.common.framework.orm.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xushanshan
 * @email 1337220620@qq.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByLoginName(String loginName);
}
