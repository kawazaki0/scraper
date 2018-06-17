package pl.elka.mjagiel1.extractor;

import java.util.Objects;

/**
 * @author Maciej Jagiello (maciej.jagiello@ntti3.com)
 */
public class Unit {
  private String quantity;
  private String matchedType;
  private String rawType;

  public Unit() {
  }

  public Unit(String quantity, String matchedType, String rawType) {
    this.quantity = quantity;
    this.matchedType = matchedType;
    this.rawType = rawType;
  }

  public String getQuantity() {
    return quantity;
  }

  public String getMatchedType() {
    return matchedType;
  }

  public String getRawType() {
    return rawType;
  }

  @Override public String toString() {
    return "Unit{" +
        "quantity='" + quantity + '\'' +
        ", matchedType='" + matchedType + '\'' +
        ", rawType='" + rawType + '\'' +
        '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Unit unit = (Unit) o;
    return Objects.equals(quantity, unit.quantity) &&
        Objects.equals(matchedType, unit.matchedType);
  }

  @Override public int hashCode() {
    return Objects.hash(quantity, matchedType);
  }
}
