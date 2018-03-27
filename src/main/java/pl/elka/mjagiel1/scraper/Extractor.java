package pl.elka.mjagiel1.scraper;

import org.jsoup.nodes.Document;
import pl.elka.mjagiel1.scraper.storage.models.Recipe;

import java.util.Optional;

public interface Extractor {
  Optional<Recipe> extractFromPage(Document page);
}
