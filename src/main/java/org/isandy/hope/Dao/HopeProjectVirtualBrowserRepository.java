package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeProjectVirtualBrowserRepository extends JpaRepository<HopeProjectVirtualBrowser, Long> {
    HopeProjectVirtualBrowser findByBrowserId(Long browserId);

    long deleteByBrowserId(Long browserId);

    List<HopeProjectVirtualBrowser> findByHopeProjectVirtualBrowserLinkAccounts_IdNull();
}