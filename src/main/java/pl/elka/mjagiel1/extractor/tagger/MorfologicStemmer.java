package pl.elka.mjagiel1.extractor.tagger;

import morfologik.stemming.PolishStemmer;
import morfologik.stemming.WordData;

import java.util.LinkedList;
import java.util.List;

public class MorfologicStemmer implements Stemmer {

  public List<Tagset> stem(String source) {
    PolishStemmer stemmer = new PolishStemmer();
    List<Tagset> result = new LinkedList<>();
    for (String word: source.split(" ")) {
      List<WordData> wordData = stemmer.lookup(word);
      if (wordData.size() == 0) {
        result.add(new Tagset(word, new Tag("xxx:xxx"), word));
      } else {
        wordData.stream().limit(1).forEach(w ->
            result.add(new Tagset(w.getWord().toString(), new Tag(w.getTag().toString()),
                w.getStem().toString())));
      }
    }
    return result;
  }

//  public static String getStemmedWord(String text) {
//    StringBuilder result = new StringBuilder();
//    PolishStemmer stemmer = new PolishStemmer();
//    text = text.trim().replaceAll(" +", " ");
//    String[] toStem = text.split(" ");
//    for (int i = 0; i < toStem.length; i++) {
//      List<WordData> lookup = stemmer.lookup(toStem[i]);
//      if (lookup.size() > 0) {
//        result.append(lookup.get(0).getStem() + " ");
//      } else {
//        result.append(toStem[i] + " ");
//      }
//    }
//    return result.toString().trim();
//  }
}
