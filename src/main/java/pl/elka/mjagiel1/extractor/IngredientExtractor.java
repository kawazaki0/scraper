package pl.elka.mjagiel1.extractor;

import pl.elka.mjagiel1.extractor.ingredientname.IngredientNormalizer;
import pl.elka.mjagiel1.extractor.unit.Unit;
import pl.elka.mjagiel1.extractor.unit.UnitExtractor;

public class IngredientExtractor {

  private UnitExtractor unitExtractor = new UnitExtractor();
  private IngredientNormalizer normalizer = new IngredientNormalizer();


  public Ingredient extract(String source) {
    Unit unit = unitExtractor.extract(source);
    String textWithoutUnits = removeUnitFromSource(unit, source);
    PredictResult<String> predictedIngredientName = normalizer.normalize(textWithoutUnits);
    return new Ingredient(predictedIngredientName.getPredictedResult(), unit);
  }

  private String removeUnitFromSource(Unit unit, String textWithoutUnits) {
    textWithoutUnits = textWithoutUnits.replace(unit.getQuantity(), "");
    textWithoutUnits = textWithoutUnits.replace(unit.getUnitType().getRawType(), "");
    return textWithoutUnits;
  }

}
