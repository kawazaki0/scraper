package pl.elka.mjagiel1.extractor.unit;

import lombok.Data;

@Data
public class Unit {
  private final String quantity;
  private final UnitType unitType;

  @Override public String toString() {
    return quantity + " " + unitType;
  }
}
