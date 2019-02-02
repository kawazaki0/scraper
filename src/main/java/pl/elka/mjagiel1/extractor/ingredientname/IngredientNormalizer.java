package pl.elka.mjagiel1.extractor.ingredientname;

import org.apache.commons.lang3.StringUtils;
import pl.elka.mjagiel1.extractor.PredictResult;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Stemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IngredientNormalizer {

  private Stemmer morfologicStemmer = new MorfologicStemmer();
  private static final int TRESHOLD = 44;
  private List<String> ingredientsList = Arrays
      .asList("agrest", "ananas", "anyż", "arbuz", "awokado", "bakłażan", "banany", "bataty",
          "bazylia", "bekon", "brokuł", "brukselka", "brzoskwinie", "burak czerwony", "bób",
          "camembert", "cebula", "chrzan", "ciecierzyca", "cukier", "cukinia", "curry", "cykoria", "cynamon",
          "cytryna", "czarna porzeczka", "czekolada", "czereśnie", "czerwona kapusta",
          "czerwone porzeczki", "czosnek", "daktyle", "dorsz", "drożdże", "dynia",
          "fasolka szparagowa", "figi", "flądra", "golonka", "granat", "grejpfrut", "groszek",
          "gruszka", "imbir", "indyk", "jabłko", "jagnięcina", "jagody", "jajka", "jarmuż",
          "jeżyny", "kaczka", "kajmak", "kakao", "kalafior", "kalarepa", "kapusta pak choi",
          "kapusta pekińska", "kardamon", "karkówka", "karp", "kasza gryczana", "kasza jęczmienna",
          "kasza manna", "kawa", "kiwi", "kokos", "kolendra", "koper włoski", "krewetki", "królik",
          "kukurydza", "kurczak", "kurkuma", "liczi", "limonka", "lubczyk", "majeranek", "makaron",
          "maliny", "mandarynki", "mango", "marcepan", "marchew", "margaryna", "mascarpone",
          "masło orzechowe", "masło", "miód", "mięta", "mleczko kokosowe", "mleko", "morele", "mozzarella",
          "mąka gryczana", "mąka orkiszowa", "mąka pszenna", "mąka tortowa", "mąka ziemniaczana", "nektarynka",
          "ogórek", "olej kokosowy", "olej rzepakowy", "olej sezamowy", "oliwa z oliwek", "oliwki",
          "oregano", "papaja", "papryka", "pieczarki", "pietruszka", "piwo", "polędwiczki",
          "pomarańcza", "pomelo", "pomidor", "por", "pstrąg", "pęczak", "płatki owsiane", "quinoa",
          "rabarbar", "roszponka", "rozmaryn", "rukola", "ryż arborio", "ryż basmati",
          "ryż jaśminowy", "salami", "sałata", "schab", "seler naciowy", "seler", "ser feta",
          "soczewica", "sos sojowy", "szałwia", "szczypiorek", "szparagi", "szpinak", "szynka",
          "słonecznik", "truskawki", "tymianek", "wino", "winogrona", "wiśnie", "wołowina",
          "ziemniaki", "łosoś", "śledź", "śliwki", "śmietana", "żurawina",
          "przecier pomidorowy", "koncentrat pomidorowy", "proszek do pieczenia");

  // kisiel, budyń, soda, galaretki, cukier wanilinowy, sól, natka pietruszki,
  // płatki kukurydziane, płatki jaglane,

  public PredictResult<String> normalize(String source) {
    String sourceLemmas = replaceWithLemmas(source);
    String sourceLemmasLowercase = sourceLemmas.trim().toLowerCase();
    Optional<String> predictedFromDatabase = findClosestLevenshtein(sourceLemmasLowercase);
    return new PredictResult<>(source, predictedFromDatabase.orElse(source), predictedFromDatabase.isPresent());
  }

  private Optional<String> findClosestLevenshtein(final String source) {
    Optional<String> result = Optional.empty();
    float maxPercent = 100;
    float currentPercent;

    for (String ingredient : ingredientsList) {
      currentPercent = (float) StringUtils.getLevenshteinDistance(source, ingredient)
          / Math.max(source.length(), ingredient.length()) * 100;
      if (currentPercent <= TRESHOLD && currentPercent < maxPercent) {
        maxPercent = currentPercent;
        result = Optional.of(ingredient);
      }
    }
    return result;
  }

  private String replaceWithLemmas(String source) {
    List<Tagset> morfologicTagsets = morfologicStemmer.stem(source);
    return morfologicTagsets.stream()
        .map(Tagset::getLemma)
        .collect(Collectors.joining(" "));
  }

}
