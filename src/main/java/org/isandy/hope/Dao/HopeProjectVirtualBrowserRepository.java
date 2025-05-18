package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeProjectVirtualBrowserRepository extends JpaRepository<HopeProjectVirtualBrowser, Long> {

    List<HopeProjectVirtualBrowser> findByHopeProjectVirtualBrowserLinkAccounts_IdNull();

    HopeProjectVirtualBrowser findByBindHost(String bindHost);
}