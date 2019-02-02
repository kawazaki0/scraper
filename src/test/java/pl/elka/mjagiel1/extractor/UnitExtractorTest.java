package pl.elka.mjagiel1.extractor;

import org.junit.Test;
import pl.elka.mjagiel1.extractor.unit.Unit;
import pl.elka.mjagiel1.extractor.unit.UnitExtractor;

import static org.junit.Assert.assertEquals;

public class UnitExtractorTest {

  private UnitExtractor unitExtractor = new UnitExtractor();

  @Test
  public void testReplaceToBaseTypeExtract() {
    assertUnit(true, "1 kg ziemniak", "kilogram", "1");
  }

  @Test
  public void testTwoLettersDifferent() {
    assertUnit(true, "5 łyżek ziemniaków", "łyżka", "5");
  }

  @Test
  public void testNumberAndTypeWithoutSpace() {
    assertUnit(true, "przecier pomidorowy 500ml", "ml", "500");
  }

  @Test
  public void testDefault() {
    assertUnit(true, "8 jajek", "szt", "8");
  }

  @Test
  public void testNoQuantityNoType() {
    assertUnit(true, "jajka", "szt", "1");
  }

  @Test
  public void testNoQuantityWithType() {
    assertUnit(true, "kostka masła", "kostka", "1");
  }

  private void assertUnit(boolean found, String source, String unitType, String unitQuantity) {
    Unit unit = unitExtractor.extract(source);
    assertEquals(unitType, unit.getUnitType().getPredictedResult());
    assertEquals(unitQuantity, unit.getQuantity().getPredictedResult());
  }
}
