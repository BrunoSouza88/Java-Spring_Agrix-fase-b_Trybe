package com.betrybe.agrix.dto;

import com.betrybe.agrix.entities.EntityCrop;
import com.betrybe.agrix.entities.EntityFarm;
import java.util.List;

/**
 * javadoc.
 */
public class DtoConverter {
  
  public static FarmDto modelToDto(EntityFarm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
 * javadoc.
 */
  public static EntityFarm dtoToModel(FarmDto dto) {
    EntityFarm farm = new EntityFarm();

    farm.setId(dto.id());
    farm.setName(dto.name());
    farm.setSize(dto.size());

    return farm;
  }

  /**
 * javadoc.
 */
  public static List<CropDto> modelToDtoCrop(List<EntityCrop> crops) {
    return crops.stream().map(crop -> new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarmId().getId()
    )).toList();
  }

  /**
 * javadoc.
 */
  public static CropDto dtoToModelCrop(EntityCrop crop) {
    return new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarmId().getId());
  }
}
