package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * javadoc.
 */
@Entity
@Table(name = "farms")
public class FarmEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String name;

  private Double size;

  @OneToMany(mappedBy = "farmId", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<CropEntity> crops;

  /**
   * javadoc.
   */
  public FarmEntity(Long id, String name, Double size, List<CropEntity> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
  }

  public FarmEntity() {
  }

  public List<CropEntity> getCrops() {
    return crops;
  }

  public void setCrops(List<CropEntity> crops) {
    this.crops = crops;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

}