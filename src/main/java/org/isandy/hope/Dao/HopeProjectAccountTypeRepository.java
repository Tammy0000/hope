package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Project.HopeProjectAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HopeProjectAccountTypeRepository extends JpaRepository<HopeProjectAccountType, Long> {
    @Query("select h from HopeProjectAccountType h where h.TypeId = ?1")
    HopeProjectAccountType findByTypeId(Long TypeId);
}