package pl.elka.mjagiel1.extractor.tagger;

import java.util.List;

public interface Stemmer {
  public List<Tagset> stem(String source);
}
