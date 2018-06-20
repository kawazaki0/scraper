package pl.elka.mjagiel1.extractor;

import org.apache.commons.lang3.StringUtils;
import pl.elka.mjagiel1.extractor.tagger.MorfologicStemmer;
import pl.elka.mjagiel1.extractor.tagger.Stemmer;
import pl.elka.mjagiel1.extractor.tagger.Tagset;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IngredientExtractor {

  private static final int TRESHOLD = 44;

  private Stemmer morfologicStemmer = new MorfologicStemmer();
  private UnitExtractor unitExtractor = new UnitExtractor();
  private List<String> ingredientsList = Arrays
      .asList("agrest", "ananas", "anyż", "arbuz", "awokado", "bakłażan", "banany", "bataty",
          "bazylia", "bekon", "brokuł", "brukselka", "brzoskwinie", "burak czerwony", "bób",
          "camembert", "cebula", "chrzan", "ciecierzyca", "cukinia", "curry", "cykoria", "cynamon",
          "cytryna", "czarna porzeczka", "czekolada", "czereśnie", "czerwona kapusta",
          "czerwone porzeczki", "czosnek", "daktyle", "dorsz", "drożdże", "dynia",
          "fasolka szparagowa", "figi", "flądra", "golonka", "granat", "grejpfrut", "groszek",
          "gruszka", "imbir", "indyk", "jabłko", "jagnięcina", "jagody", "jajka", "jarmuż",
          "jeżyny", "kaczka", "kajmak", "kakao", "kalafior", "kalarepa", "kapusta pak choi",
          "kapusta pekińska", "kardamon", "karkówka", "karp", "kasza gryczana", "kasza jęczmienna",
          "kasza manna", "kawa", "kiwi", "kokos", "kolendra", "koper włoski", "krewetki", "królik",
          "kukurydza", "kurczak", "kurkuma", "liczi", "limonka", "lubczyk", "majeranek", "makaron",
          "maliny", "mandarynki", "mango", "marcepan", "marchew", "margaryna", "mascarpone",
          "masło orzechowe", "masło", "miód", "mięta", "mleczko kokosowe", "morele", "mozzarella",
          "mąka gryczana", "mąka orkiszowa", "mąka pszenna", "mąka ziemniaczana", "nektarynka",
          "ogórek", "olej kokosowy", "olej rzepakowy", "olej sezamowy", "oliwa z oliwek", "oliwki",
          "oregano", "papaja", "papryka", "pieczarki", "pietruszka", "piwo", "polędwiczki",
          "pomarańcza", "pomelo", "pomidor", "por", "pstrąg", "pęczak", "płatki owsiane", "quinoa",
          "rabarbar", "roszponka", "rozmaryn", "rukola", "ryż arborio", "ryż basmati",
          "ryż jaśminowy", "salami", "sałata", "schab", "seler naciowy", "seler", "ser feta",
          "soczewica", "sos sojowy", "szałwia", "szczypiorek", "szparagi", "szpinak", "szynka",
          "słonecznik", "truskawki", "tymianek", "wino", "winogrona", "wiśnie", "wołowina",
          "ziemniaki", "łosoś", "śledź", "śliwki", "śmietana", "żurawina",
          "przecier pomidorowy", "koncentrat pomidorowy");


  public Ingredient extract(String source) {
    Unit unit = unitExtractor.extract(source);

    String textWithoutUnits = source;
    textWithoutUnits = textWithoutUnits.replace(unit.getQuantity(), "");
    textWithoutUnits = textWithoutUnits.replace(unit.getUnitType().getRawType(), "");

    List<Tagset> morfologicTagsets = morfologicStemmer.stem(textWithoutUnits);
    String tagged = morfologicTagsets.stream()
        .map(Tagset::getLemma)
        .collect(Collectors.joining(" "));

    String rawIngredientName = tagged.trim().toLowerCase();

    String predictedIngredientName = predictIngredientName(rawIngredientName);

    return new Ingredient(predictedIngredientName, unit);
  }


  private String predictIngredientName(final String source) {
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
    return result.orElse(source);
  }
}
