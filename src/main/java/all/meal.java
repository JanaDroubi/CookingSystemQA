package all;

import java.util.List;

public class meal {
    private String name;
    private List<Ingredient> ingredients;
double price;
    public meal(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public boolean containsAllergen(String allergen) {
        return ingredients.stream()
                .anyMatch(ing -> ing.getName().equalsIgnoreCase(allergen));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name + " [");
        for (int i = 0; i < ingredients.size(); i++) {
            builder.append(ingredients.get(i).getName());
            if (i < ingredients.size() - 1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
