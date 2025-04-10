package org.isandy.hope.Dao;

import org.isandy.hope.Dto.NewEntityDto;
import org.isandy.hope.Dto.NewEntityDtoPro;
import org.isandy.hope.Entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {

    @Query(value = "select name, description from new_entity where id = ?1", nativeQuery = true)
    List<NewEntityDtoPro> findByIds(Long id);
}