package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.FarmEntity;
import com.betrybe.agrix.models.repositories.FarmRepository;
import com.betrybe.agrix.utils.Exception;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * javadoc.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public FarmEntity insertFarm(FarmEntity farm) {
    return farmRepository.save(farm);
  }

  public List<FarmEntity> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * javadoc.
   */
  public FarmEntity getFarmById(Long id) {
    Optional<FarmEntity> oneFarm = farmRepository.findById(id);

    if (oneFarm.isPresent()) {
      return oneFarm.get();
    }
    throw new Exception();
  }
}
