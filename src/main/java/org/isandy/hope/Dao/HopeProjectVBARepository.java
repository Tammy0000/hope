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
    @Query(value = "select distinct (h.virtual_browser_index_id) from hope_project_virtual_browser_link_account h where h.virtual_browser_id = ?1 and not exists(select 1 from hope_project_virtual_browser_link_account h where h.account_type = ?2) fetch first ROW ONLY ", nativeQuery = true)
    Long findVirtualBrowserIndexIdNotInAccountType  (Long virtualBrowserId, String accountType);
}