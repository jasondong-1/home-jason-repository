package com.ideal.tagportrait.security.repository;

import com.ideal.tagportrait.entity.security.Menu;
import com.ideal.tagportrait.entity.security.RoleMenu;
import com.ideal.tagportrait.framework.orm.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author xushanshan
 * @email 1337220620@qq.com
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {
    @Query("SELECT rm.menu FROM RoleMenu rm where rm.role.id IN(?1)")
    public List<Menu> findAllInRole(List<Long> roleIds);

    @Query("SELECT rm.menu FROM RoleMenu rm WHERE rm.role.id IN(?1) AND rm.menu.parentId is NULL")
    public List<Menu> findParentMenu(List<Long> roleIds);

    @Query("SELECT rm.menu FROM RoleMenu rm WHERE rm.role.id IN(?1) AND rm.menu.parentId = ?2")
    public List<Menu> findSubMenu(List<Long> roleIds, Long parentMenuId);
}
