package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HopeProjectVirtualBrowserRepository extends JpaRepository<HopeProjectVirtualBrowser, Long> {


    HopeProjectVirtualBrowser findByBindHost(String bindHost);

    @Query("select h from HopeProjectVirtualBrowser h where h.userId = ?1")
    HopeProjectVirtualBrowser findByUserId(Long userId);

    HopeProjectVirtualBrowser findByUserIdAndBindHost(Long userId, String bindHost);
}