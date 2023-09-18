package com.betrybe.agrix.entities;

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
public class EntityFarm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private String name;

  private Double size;

  @OneToMany(mappedBy = "farmId", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<EntityCrop> crops;

  public EntityFarm() {}

  /**
 * javadoc.
 */
  public EntityFarm(Long id, String name, Double size, List<EntityCrop> crops) {
    this.id = id;

    this.name = name;

    this.size = size;

    this.crops = crops;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long newId) {
    this.id = newId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public Double getSize() {
    return this.size;
  }

  public void setSize(Double newSize) {
    this.size = newSize;
  }

  public List<EntityCrop> getCrops() {
    return this.crops;
  }

  public void setCrops(List<EntityCrop> newCrops) {
    this.crops = newCrops;
  }
}
