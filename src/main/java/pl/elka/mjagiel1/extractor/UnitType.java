package pl.elka.mjagiel1.extractor;

import lombok.Data;

@Data
class UnitType {
  private final String matchedType;
  private final String rawType;

  @Override public String toString() {
    return matchedType;
  }
}
