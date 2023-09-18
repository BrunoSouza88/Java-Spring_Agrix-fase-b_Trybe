package com.betrybe.agrix.services;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.entities.EntityCrop;
import com.betrybe.agrix.entities.EntityFarm;
import com.betrybe.agrix.repositories.RepositoryCrop;
import com.betrybe.agrix.utils.ExceptionCrop;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * javadoc.
 */
@Service
public class CropService {

  private final RepositoryCrop cropRepository;

  @Autowired
  public CropService(RepositoryCrop cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * javadoc.
   */

  public CropDto insertCrop(CropDto cropDto, EntityFarm farm) {
    EntityCrop crop = cropRepository.save(cropDto.toCrop(farm));
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarmId().getId());
  }

  public List<EntityCrop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
  * javadoc.
  */
  public EntityCrop getCropById(Long id) {
    Optional<EntityCrop> crop = cropRepository.findById(id);

    if (crop.isEmpty()) {
      throw new ExceptionCrop();
    }

    return crop.get();
  }

  /**
  * javadoc.
  */
  public List<CropDto> getCropsByFarmId(Long farmId) {
    List<EntityCrop> crop = cropRepository.getCropByFarm(farmId);
    return crop.stream().map(c -> new CropDto(
            c.getId(),
            c.getName(),
            c.getPlantedArea(),
            c.getPlantedDate(),
            c.getHarvestDate(),
            c.getFarmId().getId())).toList();
  }

}
