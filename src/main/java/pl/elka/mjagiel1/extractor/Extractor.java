package pl.elka.mjagiel1.extractor;

import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Stemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.util.List;
import java.util.stream.Collectors;

public class Extractor {

  private Stemmer morfologicStemmer = new MorfologicStemmer();
  private UnitExtractor unitExtractor = new UnitExtractor();

  public Ingredient extract(String source) {
    Unit unit = unitExtractor.extract(source);

    String textWithoutUnits = source;
    textWithoutUnits = textWithoutUnits.replace(unit.getQuantity(), "");
    textWithoutUnits = textWithoutUnits.replace(unit.getUnitType().getRawType(), "");

    List<Tagset> morfologicTagsets = morfologicStemmer.stem(textWithoutUnits);
    String tagged = morfologicTagsets.stream()
        .map(Tagset::getLemma)
        .collect(Collectors.joining(" "));

    return new Ingredient(tagged.trim().toLowerCase(), unit);
  }
}
