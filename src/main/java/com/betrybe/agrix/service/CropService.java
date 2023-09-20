package com.betrybe.agrix.service;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.ModelDtoConverter;
import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.models.entities.FarmEntity;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import com.betrybe.agrix.utils.ExceptionCrop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * javadoc.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * javadoc.
   */
  public CropDto insertOneCrop(CropDto cropDto, FarmEntity farm) {
    CropEntity oneCrop = cropRepository.save(cropDto.toCrop(farm));
    return new CropDto(
        oneCrop.getId(),
        oneCrop.getFarmId().getId(),
        oneCrop.getName(),
        oneCrop.getPlantedArea(),
        oneCrop.getPlantedDate(),
        oneCrop.getHarvestDate());
  }

  public List<CropEntity> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * javadoc.
   */
  public CropEntity getCropById(Long id) {
    Optional<CropEntity> oneCrop = cropRepository.findById(id);

    if (oneCrop.isEmpty()) {
      throw new ExceptionCrop();
    }

    return oneCrop.get();
  }

  /**
   * javadoc.
   */
  public List<CropDto> getCropsByFarmId(Long farmId) {
    List<CropEntity> crops = cropRepository.getCropsByFarm(farmId);
    return crops.stream().map(crop -> new CropDto(
        crop.getId(),
        crop.getFarmId().getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
  }

  /**
   * javadoc.
   */

  public List<CropDto> searchCrop(LocalDate start, LocalDate end) {
    List<CropEntity> crops = cropRepository.findByHarvestDateBetween(start, end);
    return ModelDtoConverter.dtoToModelCrop(crops);
  }
}
