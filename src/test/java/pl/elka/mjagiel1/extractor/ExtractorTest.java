package pl.elka.mjagiel1.extractor;

import org.annolab.tt4j.TreeTaggerException;
import org.junit.Before;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.Extractor;
import pl.elka.mjagiel1.extractor.Ingredient;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExtractorTest {

  Extractor extractor;

  @Before
  public void setUp() throws Exception {
    extractor = new Extractor();
  }

  @Test
  public void testExtractor() throws Exception {
    assertIngredientExtract("1 kg ziemniaków", "1", "kilogram", "ziemniak");
    assertIngredientExtract("przecier pomidorowy 500ml", "500", "ml", "przecier pomidorowy");
    assertIngredientExtract("Czosnek 2 ząbki", "2", "ząbek", "czosnek");
    assertIngredientExtract("1 łyżka koncentratu pomidorowego", "1", "łyżka", "koncentrat pomidorowy");
  }

  private void assertIngredientExtract(String source, String quantity, String type, String name) throws IOException, TreeTaggerException {
    Ingredient ingredient = extractor.extract(source);
    assertEquals(quantity, ingredient.getUnit().get().getQuantity());
    assertEquals(type, ingredient.getUnit().get().getMatchedType());
    assertEquals(name, ingredient.getName());
  }

}
