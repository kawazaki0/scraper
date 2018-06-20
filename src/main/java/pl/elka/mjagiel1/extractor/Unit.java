package pl.elka.mjagiel1.extractor;

import lombok.Data;

@Data
class Unit {
  private final String quantity;
  private final UnitType unitType;

  @Override public String toString() {
    return quantity + " " + unitType;
  }
}
