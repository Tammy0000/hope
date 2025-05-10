package org.isandy.hope.Dao;

import org.isandy.hope.Entity.User.HopeUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopeUserStatusRepository extends JpaRepository<HopeUserStatus, Long> {
    boolean existsByUserIdAndIsBan(Long userId, boolean isBan);
}