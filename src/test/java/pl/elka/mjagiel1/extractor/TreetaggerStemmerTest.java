package pl.elka.mjagiel1.extractor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.elka.mjagiel1.extractor.tagger.TreetaggerStemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TreetaggerStemmerTest {

  private TreetaggerStemmer stemmer;

  @Before
  public void setUp() throws Exception {
    stemmer = new TreetaggerStemmer();
  }

  @After
  public void tearDown() throws Exception {
    stemmer.close();
  }

  @Test
  public void testTagger() throws Exception {
    List<Tagset> tags = stemmer.stem("4 kilogramy ziemniaków");

    assertEquals(
        Arrays.asList("num", "subst", "subst"),
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
    List<Tagset> tags = stemmer.stem("przecieru pomidorowego 500ml");

    assertEquals(
        Arrays.asList("subst", "adj", "subst"),
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
