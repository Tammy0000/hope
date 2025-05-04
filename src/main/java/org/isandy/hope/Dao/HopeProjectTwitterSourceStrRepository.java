package org.isandy.hope.Dao;

import org.isandy.hope.Entity.HopeProjectTwitterSourceStr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeProjectTwitterSourceStrRepository extends JpaRepository<HopeProjectTwitterSourceStr, Long> {
    List<HopeProjectTwitterSourceStr> findByIsUseAndUserId(Boolean isUse, Long userId);
}