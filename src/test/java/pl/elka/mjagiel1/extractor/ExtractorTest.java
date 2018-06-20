package pl.elka.mjagiel1.extractor;

import org.annolab.tt4j.TreeTaggerException;
import org.junit.Before;
import org.junit.Test;

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

  @Test
  public void testTest() throws Exception {
    assertIngredientExtract("kostka masła", "1", "kostka", "masło");
  }

  private void assertIngredientExtract(String source, String quantity, String type, String name) throws IOException, TreeTaggerException {
    Ingredient ingredient = extractor.extract(source);
    assertEquals(quantity, ingredient.getUnit().getQuantity());
    assertEquals(type, ingredient.getUnit().getUnitType().getMatchedType());
    assertEquals(name, ingredient.getName());
  }

}
