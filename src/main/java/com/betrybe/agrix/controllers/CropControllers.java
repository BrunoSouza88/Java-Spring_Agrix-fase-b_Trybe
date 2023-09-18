package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.DtoConverter;
import com.betrybe.agrix.entities.EntityCrop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropControllers {
  @Autowired
  private final CropService cropService;

  @Autowired
  public CropControllers(CropService cropService) {
    this.cropService = cropService;
  }

  /**
  * javadoc.
  */
  @GetMapping()
  public List<CropDto> getAllFarms() {
    List<EntityCrop> allCrops = cropService.getAllCrops();
    return allCrops.stream().map((crop) -> new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarmId().getId()
    )).collect(Collectors.toList());
  }

  /**
  * javadoc.
  */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    EntityCrop crop = cropService.getCropById(id);
    CropDto cropDto = DtoConverter.dtoToModelCrop(crop);
    return ResponseEntity.ok(cropDto);
  }
}
