package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SmakerExtractor implements Extractor {

  @Override public Optional<Recipe> extractFromPage(Document doc) {
    Element recipeName = doc.select("h1[itemprop=name]").first();

    if (recipeName == null || recipeName.text().isEmpty()) {
      System.err.println(doc.baseUri());
      return Optional.empty();
    }

    List<String> tags = new ArrayList<>();
    Difficulty difficulty = Difficulty.UNKNOWN;
    PreparationTime preparationTime = PreparationTime.UNKNOWN;
    Map<String, String> metaValues = new HashMap<>();
    List<String> ingredients = new ArrayList<>();

    doc.select("span[itemprop=ingredients]").stream()
        .map(Element::text)
        .forEach(ingredients::add);

    String recipeText = doc.select(".preparation").first().text();

    return Optional.of(new Recipe(recipeName.text(), ingredients, recipeText, tags, difficulty,
        preparationTime, metaValues));
  }
}
