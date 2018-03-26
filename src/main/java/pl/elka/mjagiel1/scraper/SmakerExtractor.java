package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmakerExtractor implements Extractor {

  @Override public WebRecipe extractFromPage(Document doc) {
    String recipeName = doc.select("h1[itemprop=name]").first().text();

    List<String> tags = new ArrayList<>();
    Difficulty difficulty = Difficulty.UNKNOWN;
    PreparationTime preparationTime = PreparationTime.UNKNOWN;
    Map<String, String> metaValues = new HashMap<>();
    List<String> ingredients = new ArrayList<>();

    doc.select("span[itemprop=ingredients]").stream()
        .map(Element::text)
        .forEach(ingredients::add);

    String recipeText = doc.select(".preparation").first().text();
    List<String> hrefs = new ArrayList<>();

    doc.select("a[href]").stream()
        .map(Element::text)
        .forEach(hrefs::add);

    RecipeWebMeta webMeta = new RecipeWebMeta(doc, doc.baseUri(), hrefs);
    return new WebRecipe(recipeName, tags, difficulty, preparationTime,
        metaValues, ingredients, recipeText, webMeta);
  }
}
