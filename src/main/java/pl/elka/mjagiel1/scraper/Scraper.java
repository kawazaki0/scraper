package pl.elka.mjagiel1.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import java.io.IOException;
import java.util.Optional;

@Component
public class Scraper {

  @Autowired
  public Scraper() {
  }

  public Optional<Recipe> download(String url) {
    try {
      Document doc = Jsoup.connect(url)
          .userAgent(
              "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 Safari/537.36")
          .timeout(3000)
          .get();


      Extractor extractor = getExtractorByUrl(url);
      return extractor.extractFromPage(doc);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private Extractor getExtractorByUrl(String url) {
    if (url.startsWith("https://smaker.pl/")) {
      return new SmakerExtractor();
    }
    throw new RuntimeException("No matching extractor");
  }
}
