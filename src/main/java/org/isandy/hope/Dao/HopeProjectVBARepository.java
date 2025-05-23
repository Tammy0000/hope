package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HopeProjectVBARepository extends JpaRepository<HopeProjectVirtualBrowserLinkAccount, Long> {
    List<HopeProjectVirtualBrowserLinkAccount> findByUserId(Long userId);

    /**
     * 根据浏览器ID和需要排除的账号类型查询虚拟浏览器索引ID （如果是例如某一组有 tg和dc 而没有tw,则返回该组的index_id）
     * @param virtualBrowserId 浏览器ID
     * @return 虚拟浏览器索引ID
     */
    //todo 根据浏览器ID和需要排除的账号类型查询虚拟浏览器索引ID
    @Query(value = "select virtual_browser_index_id from hope_project_virtual_browser_link_account where user_id = ?1 and virtual_browser_id = ?2 group by virtual_browser_index_id having count(case when account_type = ?3 then 1 end ) = 0 fetch first ROW ONLY;", nativeQuery = true)
    Long findVirtualBrowserIndexIdNotInAccountType  (Long userId, Long virtualBrowserId, String accountType);

    /**
     * @description 根据用户ID、虚拟浏览器ID和虚拟浏览器索引ID查询虚拟浏览器关联账号实体类
     * @param userId 用户ID
     * @param virtualBrowserId 虚拟浏览器ID
     * @param virtualBrowserIndexId 虚拟浏览器关联索引ID
     * @return 虚拟浏览器关联账号实体类
     */
    @Query("""
            select h from HopeProjectVirtualBrowserLinkAccount h
            where h.userId = ?1 and h.virtualBrowserId = ?2 and h.virtualBrowserIndexId = ?3""")
    HopeProjectVirtualBrowserLinkAccount findByUserIdAndVirtualBrowserIdAndVirtualBrowserIndexId(Long userId, Long virtualBrowserId, Long virtualBrowserIndexId);


}