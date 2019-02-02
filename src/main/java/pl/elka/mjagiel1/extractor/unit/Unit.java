package pl.elka.mjagiel1.extractor.unit;

import lombok.Data;
import pl.elka.mjagiel1.extractor.PredictResult;

@Data
public class Unit {
  private final PredictResult<String> quantity;
  private final PredictResult<String> unitType;

  @Override public String toString() {
    return quantity + " " + unitType;
  }
}
