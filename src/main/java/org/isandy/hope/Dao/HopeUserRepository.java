package org.isandy.hope.Dao;

import org.isandy.hope.Entity.User.HopeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HopeUserRepository extends JpaRepository<HopeUser, Long> {
    @Query("select (count(h) > 0) from HopeUser h where h.username = ?1")
    boolean existsByUsername(String username);

    @Query("select (count(h) > 0) from HopeUser h where h.phone = ?1")
    boolean existsByPhone(String phone);

    boolean existsByUserId(Long userId);

    @Query("select h from HopeUser h where h.phone = ?1")
    HopeUser findByPhone(String phone);
}