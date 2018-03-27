package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SmakerExtractor implements Extractor {

  @Override public Optional<Recipe> extractFromPage(Document doc) {
    Element recipeName = doc.select("h1[itemprop=name]").first();

    if (recipeName == null || recipeName.text().isEmpty()) {
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

    String prefixUrl = getPrefixUrl(doc);

    List<String> hrefs = doc.select("a[href]").stream()
        .map(e -> e.attr("href"))
        .map(e -> addHost(e, prefixUrl))
        .filter(e -> e.contains(prefixUrl))
        .distinct()
        .collect(Collectors.toList());

    return Optional.of(new Recipe(recipeName.text(), ingredients, recipeText, tags, difficulty,
        preparationTime, metaValues, doc.baseUri(), doc.html(), hrefs));
  }

  private String getPrefixUrl(Document doc) {
    String prefixUrl = "";
    try {
      URI baseUri = new URI(doc.baseUri());
      String host = baseUri.getHost();
      prefixUrl = baseUri.getScheme() + "://" + host;
    } catch (URISyntaxException ignored) {
    }
    return prefixUrl;
  }

  private String addHost(String href, String prefixUrl) {
    if (href.startsWith("/")) {
      return prefixUrl + href;
    } else {
      return href;
    }
  }
}
