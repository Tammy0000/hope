package org.isandy.hope.Dao;

import org.isandy.hope.Entity.User.HopeUserSecurityMnemonic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MnemonicRepository extends JpaRepository<HopeUserSecurityMnemonic, Long> {
}