package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.models.entities.FarmEntity;
import java.time.LocalDate;

/**
 * javadoc.
 */
public record CropDto(
    Long id,
    Long farmId,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate) {
  public CropEntity toCrop(FarmEntity farmId) {
    return new CropEntity(id, farmId, name, plantedArea, plantedDate, harvestDate, null);
  }
}
