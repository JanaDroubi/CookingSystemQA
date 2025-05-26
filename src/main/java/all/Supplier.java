package all;

import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private String name;
    private Map<Ingredient, Double> ingredientPrices; // Ingredient object → price

    public Supplier(String name) {
        this.name = name;
        this.ingredientPrices = new HashMap<>();
    }

    // Set price for a specific ingredient
    public void setPrice(Ingredient ingredient, double price) {
        ingredientPrices.put(ingredient, price);
    }

    // Get price for a specific ingredient
    public double getPrice(Ingredient ingredient) {
        return ingredientPrices.getOrDefault(ingredient, -1.0); // Return -1.0 if not found
    }

    public String getName() {
        return name;
    }

    // Convenience method to add ingredient and price
    public void addIngredientPrice(Ingredient ingredient, double price) {
        setPrice(ingredient, price);
    }

    // Get all ingredients and prices for display
    public Map<Ingredient, Double> getIngredientPrices() {
        return ingredientPrices;
    }
}
