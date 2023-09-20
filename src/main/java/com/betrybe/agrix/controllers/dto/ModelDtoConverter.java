package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.CropEntity;
import com.betrybe.agrix.models.entities.FarmEntity;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;

/**
 * javadoc.
 */

public class ModelDtoConverter {

  /**
   * javadoc.
   */
  public static FarmDto modelToDto(FarmEntity farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * javadoc.
   */
  public static FarmEntity dtoToModel(FarmDto dto) {
    FarmEntity farm = new FarmEntity();

    farm.setId(dto.id());
    farm.setName(dto.name());
    farm.setSize(dto.size());

    return farm;
  }

  /**
   * javadoc.
   */
  public static CropDto modelToDtoCrop(CropEntity crop) {
    return new CropDto(
        crop.getId(),
        crop.getFarmId().getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }

  /**
   * javadoc.
   */
  public static FertilizerDto modelToDtoFert(Fertilizer fert) {
    return new FertilizerDto(
        fert.getId(),
        fert.getName(),
        fert.getBrand(),
        fert.getComposition()
    );
  }

  /**
   * javadoc.
   */
  public static List<CropDto> dtoToModelCrop(List<CropEntity> crops) {
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
  public static List<FertilizerDto> dtoToModelFert(List<Fertilizer> fert) {
    return fert.stream().map(f -> new FertilizerDto(
        f.getId(),
        f.getName(),
        f.getBrand(),
        f.getComposition())).toList();
  }

}