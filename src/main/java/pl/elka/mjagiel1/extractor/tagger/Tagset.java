package pl.elka.mjagiel1.extractor.tagger;

public class Tagset {
  String token;
  Tag tag;
  String lemma;

  public Tagset(String token, Tag tag, String lemma) {
    this.token = token;
    this.tag = tag;
    this.lemma = lemma;
  }

  @Override public String toString() {
    return "Tagset{" +
        "token='" + token + '\'' +
        ", tag='" + tag + '\'' +
        ", lemma='" + lemma + '\'' +
        '}';
  }

  public String getToken() {
    return token;
  }

  public Tag getTag() {
    return tag;
  }

  public String getLemma() {
    return lemma;
  }
}
