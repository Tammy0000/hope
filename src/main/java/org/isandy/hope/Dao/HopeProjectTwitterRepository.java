package org.isandy.hope.Dao;

import org.isandy.hope.Entity.HopeProjectTwitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeProjectTwitterRepository extends JpaRepository<HopeProjectTwitter, Long> {
    boolean existsByTwitterAccount(String twitterAccount);

    List<HopeProjectTwitter> findByUserIdAndIsEditPassword(Long userId, Boolean isEditPassword);
}