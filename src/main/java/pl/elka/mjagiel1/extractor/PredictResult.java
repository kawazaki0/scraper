package pl.elka.mjagiel1.extractor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredictResult<T> {
  public String rawSource;
  public T predictedResult;
  public boolean found;
}
