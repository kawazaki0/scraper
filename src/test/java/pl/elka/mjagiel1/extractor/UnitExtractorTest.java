package pl.elka.mjagiel1.extractor;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitExtractorTest {
  @Test
  public void testExtract() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("1 kg ziemniak").get();
    assertEquals("kilogram", unit.getMatchedType());
    assertEquals("1", unit.getQuantity());
  }
  @Test
  public void testExtract2() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("5 łyżek ziemniaków").get();
    assertEquals("łyżka", unit.getMatchedType());
    assertEquals("5", unit.getQuantity());
  }
  @Test
  public void testExtract3() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("przecier pomidorowy 500ml").get();
    assertEquals("ml", unit.getMatchedType());
    assertEquals("500", unit.getQuantity());
  }
  @Test
  public void testExtract4() {
    UnitExtractor unitExtractor = new UnitExtractor();
    Unit unit = unitExtractor.extract("8 jajek").get();
    assertEquals("szt", unit.getMatchedType());
    assertEquals("8", unit.getQuantity());
  }
}
