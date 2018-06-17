package pl.elka.mjagiel1.extractor.tagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tag {
  private String tagClass;
  private List<String> categories;

  public Tag(String sourceString) {
    List<String> sourceList = Arrays.asList(sourceString.split(":"));
    tagClass = sourceList.get(0);
    categories = new ArrayList<>();
    categories.addAll(sourceList);
    categories.remove(0);
  }

  public String getTagClass() {
    return tagClass;
  }

  public List<String> getCategories() {
    return categories;
  }

  @Override public String toString() {
    return "Tag{" +
        "tagClass='" + tagClass + '\'' +
        ", categories=" + categories +
        '}';
  }
}
