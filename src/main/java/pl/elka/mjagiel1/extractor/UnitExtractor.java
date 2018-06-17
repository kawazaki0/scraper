package pl.elka.mjagiel1.extractor;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UnitExtractor {

  private static final int TRESHOLD = 44;
  private Map<String, List<String>> model = new HashMap<>();



  public UnitExtractor() {
    this.model.put("sztuka", Arrays.asList("sztuka", "szt", ""));
    this.model.put("litr", Arrays.asList("litr", "l"));
    this.model.put("ml", Arrays.asList("ml", "mililitry"));
    this.model.put("kilogram", Arrays.asList("kilogram", "kg"));
    this.model.put("gram", Arrays.asList("gram", "g"));
    this.model.put("dag", Arrays.asList("dag"));
    this.model.put("łyżka", Arrays.asList("łyżka", "łyżek"));
    this.model.put("łyżeczka", Arrays.asList("łyżeczka"));
    this.model.put("szklanka", Arrays.asList("szklanka", "szkl"));
    this.model.put("ząbek", Arrays.asList("ząbek"));
    this.model.put("garść", Arrays.asList("garść"));
    this.model.put("kawałek", Arrays.asList("kawałek"));
    this.model.put("pęczek", Arrays.asList("pęczek"));
    this.model.put("plaster", Arrays.asList("plaster"));
    this.model.put("puszka", Arrays.asList("puszka"));
    this.model.put("opakowanie", Arrays.asList("opakowanie", "op"));
  }

  public Optional<Unit> extract(String source) {
    return getUnit(source);
  }

  public Optional<Unit> getUnit(final String source) {
    Optional<String> result = Optional.empty();
    String choosed = null;
    float maxPercent = 100;
    float currentPercent;

    final String sourceWithoutNonLetters = source.replaceAll("[^\\p{L}]", " ");
    final String sourceTrimmed = sourceWithoutNonLetters.trim();
    final String sourcePreprocessed = sourceTrimmed.toLowerCase();
    String[] listOfWords = sourcePreprocessed.split(" ");

    List<String> units = model.values().stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    for (String word : listOfWords) {
      for (String unit : units) {
        currentPercent = (float) StringUtils.getLevenshteinDistance(word, unit)
            / Math.max(word.length(), unit.length()) * 100;
        if (currentPercent <= TRESHOLD && currentPercent < maxPercent) {
          maxPercent = currentPercent;
          result = model.entrySet().stream()
                  .filter(entry -> entry.getValue().contains(unit))
                  .map(Map.Entry::getKey)
                  .findFirst();
          choosed = word;
        }
      }
    }
    if (!result.isPresent()) {
      return Optional.of(new Unit(getQuantity(source), "szt", ""));
    }
    return Optional.of(new Unit(getQuantity(source), result.get(), choosed));
  }

  private static String getQuantity(String source) {
    String quantity;
    Pattern pattern = Pattern
        .compile("(\\d+([,/\\-\\.]\\d+)?)\\s?(\\p{L}+)");
    Matcher matcher = pattern.matcher(source);
    if (matcher.find()) {
      quantity = matcher.group(1);
    } else {
      quantity = "1";
    }
    return quantity;
  }
}
