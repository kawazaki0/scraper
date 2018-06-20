package pl.elka.mjagiel1.extractor;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitExtractorTest {
  @Test
  public void testReplaceToBaseTypeExtract() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("1 kg ziemniak");
    assertEquals("kilogram", unit.getUnitType().getMatchedType());
    assertEquals("1", unit.getQuantity());
  }
  @Test
  public void testTwoLettersDifferent() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("5 łyżek ziemniaków");
    assertEquals("łyżka", unit.getUnitType().getMatchedType());
    assertEquals("5", unit.getQuantity());
  }
  @Test
  public void testNumberAndTypeWithoutSpace() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("przecier pomidorowy 500ml");
    assertEquals("ml", unit.getUnitType().getMatchedType());
    assertEquals("500", unit.getQuantity());
  }
  @Test
  public void testDefault() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("8 jajek");
    assertEquals("szt", unit.getUnitType().getMatchedType());
    assertEquals("8", unit.getQuantity());
  }
  @Test
  public void testNoQuantityNoType() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("jajka");
    assertEquals("szt", unit.getUnitType().getMatchedType());
    assertEquals("1", unit.getQuantity());
  }
  @Test
  public void testNoQuantityWithType() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("kostka masła");
    assertEquals("kostka", unit.getUnitType().getMatchedType());
    assertEquals("1", unit.getQuantity());
  }
}
