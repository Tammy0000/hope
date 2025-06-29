package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectVirtualBrowserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopeProjectVBLRepository extends JpaRepository<HopeProjectVirtualBrowserLog, Long> {
}