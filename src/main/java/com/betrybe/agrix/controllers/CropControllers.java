package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.ModelDtoConverter;
import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    List<CropEntity> allCrops = cropService.getAllCrops();
    return allCrops.stream()
        .map((crop) -> new CropDto(
            crop.getId(),
            crop.getFarmId().getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate()
            ))
        .collect(Collectors.toList());
  }

  /**
   * javadoc.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    CropEntity crop = cropService.getCropById(id);
    CropDto cropDto = ModelDtoConverter.modelToDtoCrop(crop);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * javadoc.
   */
  @GetMapping("/search")
  public ResponseEntity<?> getSearch(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    return ResponseEntity.ok(cropService.searchCrop(start, end));
  }

}
