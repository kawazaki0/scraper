package pl.elka.mjagiel1.extractor;

import pl.elka.mjagiel1.extractor.IngredientLabeler.Labels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class LabelReplacer {

  public List<Labels> replace(String source, Ingredient ingredient) {
    List<String> sourceWords = Arrays.asList(source.split(" "));
    List<Labels> sourceLabels =
        new ArrayList<>(Collections.nCopies(source.split(" ").length, Labels.OUTSIDE));

    replaceIngredientName(ingredient.getName().getRawSource(), sourceWords, sourceLabels,
        Labels.BEGIN_INGREDIENT, Labels.INSIDE_INGREDIENT);
    replaceIngredientName(ingredient.getUnit().getQuantity().getRawSource(), sourceWords,
        sourceLabels,
        Labels.BEGIN_QUANTITY, Labels.INSIDE_QUANTITY);
    replaceIngredientName(ingredient.getUnit().getUnitType().getRawSource(), sourceWords,
        sourceLabels,
        Labels.BEGIN_UNIT, Labels.INSIDE_UNIT);
    return sourceLabels;
  }

  private void replaceIngredientName(String ingredientRawEntity, List<String> sourceWords,
      List<Labels> sourceLabels, Labels beginLabel, Labels insideLabel) {
    boolean isFirst = true;
    for (String word : ingredientRawEntity.split(" ")) {
      if (isFirst) {
        int index = sourceWords.indexOf(word);
        if (index >= 0) {
          sourceLabels.set(index, beginLabel);
          isFirst = false;
        }
      } else {
        int index = sourceWords.indexOf(word);
        if (index >= 0) {
          sourceLabels.set(index, insideLabel);
        }
      }
    }
  }
}
