package pl.elka.mjagiel1.extractor.tagger;

import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TreetaggerStemmer implements Closeable, Stemmer {

  private TreeTaggerWrapper<String> wrapper;

  public TreetaggerStemmer() {
    System.setProperty("treetagger.home", "/home/m/inzynierka/treetagger");
    TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<>();
    try {
      tt.setModel("polish-utf8.par:utf-8");
    } catch (IOException e) {
      throw new RuntimeException("Model not found");
    }
    this.wrapper = tt;
  }

  @Override public List<Tagset> stem(String source) {
    try {
      return stem(Arrays.asList(source.split(" ")));
    } catch (IOException | TreeTaggerException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Tagset> stem(List<String> source) throws IOException, TreeTaggerException {
    List<Tagset> l = new LinkedList<>();
    wrapper.setHandler((token, pos, lemma) -> l.add(new Tagset(token, new Tag(pos), lemma)));
    wrapper.process(source);
    return l;
  }

  @Override public void close() {
    wrapper.destroy();
  }

}
