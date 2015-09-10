package com.ideal.tagportrait.common.security.repository;

import com.ideal.tagportrait.common.entity.security.Role;
import com.ideal.tagportrait.common.entity.security.UserRole;
import com.ideal.tagportrait.common.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xushanshan
 * @email 1337220620@qq.com
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT ur.role FROM UserRole ur where ur.user.id=?1")
    public List<Role> findRoleByUser(Long id);

    @Query("DELETE FROM UserRole ur where ur.user.id=?1")
    @Modifying
    public void deleteByUser(Long id);
}
