package org.isandy.hope.Dao;

import org.isandy.hope.Entity.Mnemonic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MnemonicRepository extends JpaRepository<Mnemonic, Long> {
}