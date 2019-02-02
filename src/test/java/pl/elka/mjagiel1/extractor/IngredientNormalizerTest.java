package pl.elka.mjagiel1.extractor;

import org.junit.Before;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.ingredientname.IngredientNormalizer;

import static org.junit.Assert.assertEquals;

public class IngredientNormalizerTest {


  IngredientNormalizer normalizer;

  @Before
  public void setUp() throws Exception {
    normalizer = new IngredientNormalizer();
  }

  @Test
  public void test() {
    assertNormalize(true, "przecier pomidorowy", "przecieru pomidorowego");
    assertNormalize(true, "mleko", "mleka");
    assertNormalize(false, "aasdfasdfasdf mleko", "aasdfasdfasdf mleko");
  }

  private void assertNormalize(boolean found, String expected, String source) {
    PredictResult<String> predictResult = normalizer.normalize(source);
    assertEquals(expected, predictResult.getPredictedResult());
    assertEquals(found, predictResult.isFound());
  }

}
