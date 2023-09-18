package com.betrybe.agrix.repositories;

import com.betrybe.agrix.entities.EntityCrop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */
@Repository
public interface RepositoryCrop extends JpaRepository<EntityCrop, Long> {
  @Query(value = "SELECT * FROM crops WHERE crops.farm_id = :farmId", nativeQuery = true)
  List<EntityCrop> getCropByFarm(Long farmId);
}
