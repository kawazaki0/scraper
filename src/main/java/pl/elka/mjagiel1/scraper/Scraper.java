package pl.elka.mjagiel1.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;
import pl.elka.mjagiel1.scraper.storage.models.Site;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Scraper {

  @Autowired
  public Scraper() {
  }

  public Site download(String url) {
    try {
      Document doc = Jsoup.connect(url)
          .userAgent(
              "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 Safari/537.36")
          .timeout(3000)
          .get();

      Extractor extractor = getExtractorByUrl(url);
      Optional<Recipe> recipe = extractor.extractFromPage(doc);

      List<String> hrefs = extractHrefs(doc);

      return new Site(doc.baseUri(), doc.html(), hrefs, recipe.orElse(null));
    } catch (IOException | IllegalArgumentException e) {
      return new Site(url, "error", null, null);
    }
  }

  private List<String> extractHrefs(Document doc) {
    String prefixUrl = getPrefixUrl(doc);
    return doc.select("a[href]").stream()
        .map(e -> e.attr("href"))
        .map(e -> addHost(e, prefixUrl))
        .filter(e -> e.contains(prefixUrl))
        .distinct()
        .collect(Collectors.toList());
  }

  private Extractor getExtractorByUrl(String url) throws IllegalArgumentException {
    if (url.startsWith("https://smaker.pl/")) {
      return new SmakerExtractor();
    }
    throw new IllegalArgumentException("No matching extractor");
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
