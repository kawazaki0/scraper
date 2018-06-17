package pl.elka.mjagiel1.extractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Tag;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class MorfologicStemmerTest {

  MorfologicStemmer morfologicStemmer;

  @Before
  public void setUp() throws Exception {
    morfologicStemmer = new MorfologicStemmer();
  }

  @Test
  public void testStem() {
    List<Tagset> tags = morfologicStemmer.stem("4 kilogramy ziemniaków");
    assertEquals(
        Arrays.asList("xxx", "subst", "subst"),
        tags.stream()
            .map(tt -> tt.getTag().getTagClass())
            .collect(Collectors.toList()));

    assertEquals(
        Arrays.asList("4", "kilogram", "ziemniak"),
        tags.stream()
            .map(Tagset::getLemma)
            .collect(Collectors.toList()));
  }

  @Test
  public void testStem2() {
    List<Tagset> tags = morfologicStemmer.stem("przecieru pomidorowego 500ml");
    assertEquals(
        Arrays.asList("subst", "adj", "xxx"),
        tags.stream()
            .map(tt -> tt.getTag().getTagClass())
            .collect(Collectors.toList()));

    assertEquals(
        Arrays.asList("przecier", "pomidorowy", "500ml"),
        tags.stream()
            .map(Tagset::getLemma)
            .collect(Collectors.toList()));
  }
}
