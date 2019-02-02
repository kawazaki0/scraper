package pl.elka.mjagiel1.extractor.unit;

import lombok.Data;

@Data
public class UnitType {
  private final String matchedType;
  private final String rawType;

  @Override public String toString() {
    return matchedType;
  }
}
