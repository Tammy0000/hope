package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLinkAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeProjectVBARepository extends JpaRepository<HopeProjectVirtualBrowserLinkAccount, Long> {
    List<HopeProjectVirtualBrowserLinkAccount> findByUserId(Long userId);
}