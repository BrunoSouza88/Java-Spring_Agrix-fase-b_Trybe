package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.FarmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */
@Repository
public interface FarmRepository extends JpaRepository<FarmEntity, Long> {

}
