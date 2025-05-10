package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Auth.HopeAuthPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeAuthPathRepository extends JpaRepository<HopeAuthPath, Long> {
    List<HopeAuthPath> findByAuth(boolean auth);
}