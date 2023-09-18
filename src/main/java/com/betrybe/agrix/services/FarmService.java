package com.betrybe.agrix.services;

import com.betrybe.agrix.entities.EntityFarm;
import com.betrybe.agrix.repositories.RepositoryFarm;
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
  
  private final RepositoryFarm farmRepository;

  @Autowired
  public FarmService(RepositoryFarm farmRepository) {
    this.farmRepository = farmRepository;
  }

  public EntityFarm insertFarm(EntityFarm farm) {
    return farmRepository.save(farm);
  }

  public List<EntityFarm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
 * javadoc.
 */
  public EntityFarm getFarmbyId(Long id) {
    Optional<EntityFarm> farm = farmRepository.findById(id);

    if (farm.isPresent()) {
      return farm.get();
    }

    throw new Exception();
  }
}
