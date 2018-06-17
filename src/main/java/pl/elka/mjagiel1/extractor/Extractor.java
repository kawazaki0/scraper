package pl.elka.mjagiel1.extractor;

import org.annolab.tt4j.TreeTaggerException;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Stemmer;
import pl.elka.mjagiel1.extractor.tagger.TreetaggerStemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Extractor {

  Stemmer morfologicStemmer = new MorfologicStemmer();
  UnitExtractor unitExtractor = new UnitExtractor();

  public Ingredient extract(String source) {
    Optional<Unit> unit = unitExtractor.extract(source);

    String textWithoutUnits = source;
    if (unit.isPresent()) {
      textWithoutUnits = textWithoutUnits.replace(unit.get().getQuantity(), "");
      textWithoutUnits = textWithoutUnits.replace(unit.get().getRawType(), "");
    }

    List<Tagset> morfologicTagsets = morfologicStemmer.stem(textWithoutUnits);
    String tagged = morfologicTagsets.stream()
        .map(Tagset::getLemma)
        .collect(Collectors.joining(" "));

    return new Ingredient(tagged.trim().toLowerCase(), unit);
  }
}
