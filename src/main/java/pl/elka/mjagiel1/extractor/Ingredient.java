package pl.elka.mjagiel1.extractor;

import lombok.Data;
import pl.elka.mjagiel1.extractor.unit.Unit;

@Data
public class Ingredient {

  private final PredictResult<String> name;
  private final Unit unit;

  @Override public String toString() {
    return "Ingredient{" + unit + " | " + name + '}';
  }

  //  private String category;


//  public void setCategory(String category) {
//    if (category == null) {
//      category = "";
//    }
//    if (category.equals("Breakfast Cereals Cereal")) {
//      this.category = "CerealProduct";
//
//    } else if (category.equals("Cereal Grains and Pasta")) {
//      this.category = "CerealProduct";
//
//    } else if (category.equals("Baked Products")) {
//      this.category = "BakedProduct";
//
//    } else if (category.equals("Beverages")) {
//      this.category = "BeverageProduct";
//
//    } else if (category.equals("Dairy and Egg Products")) {
//      this.category = "DairyProduct";
//
//    } else if (category.equals("Finfish and Shellfish Products")) {
//      this.category = "FishProduct";
//
//    } else if (category.equals("Fruits and Fruit Juices")) {
//      this.category = "FruitProduct";
//
//    } else if (category.equals("Beef Products")) {
//      this.category = "MeatProduct";
//
//    } else if (category.equals("Lamb, Veal, and Game Products")) {
//      this.category = "MeatProduct";
//
//    } else if (category.equals("Pork Products")) {
//      this.category = "MeatProduct";
//
//    } else if (category.equals("Poultry Products")) {
//      this.category = "MeatProduct";
//
//    } else if (category.equals("Sausages and Luncheon Meats")) {
//      this.category = "MeatProduct";
//
//    } else if (category.equals("Fats and Oils")) {
//      this.category = "FatAndOilProduct";
//
//    } else if (category.equals("Sweets")) {
//      this.category = "SweetProduct";
//
//    } else if (category.equals("Spices and Herbs")) {
//      this.category = "SpiceProduct";
//
//    } else if (category.equals("Legumes and Legume Products")) {
//      this.category = "VegetableProduct";
//
//    } else if (category.equals("Vegetables and Vegetable Products")) {
//      this.category = "VegetableProduct";
//
//    } else {
//      this.category = "OtherProduct";
//    }
//  }

}
