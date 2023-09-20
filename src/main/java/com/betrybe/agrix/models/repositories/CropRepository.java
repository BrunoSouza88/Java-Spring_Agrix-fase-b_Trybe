package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.CropEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */
@Repository
public interface CropRepository extends JpaRepository<CropEntity, Long> {
  @Query(value = "SELECT * FROM crops WHERE crops.farm_id = :farmId", nativeQuery = true)
  List<CropEntity> getCropsByFarm(Long farmId);

  List<CropEntity> findByHarvestDateBetween(LocalDate start, LocalDate end);

}
