package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
  @Query("SELECT fert FROM Fertilizer fert INNER JOIN fert.crops cro WHERE cro.id = :cropId")
  List<Fertilizer> getFertializerByCrop(Long cropId);

}