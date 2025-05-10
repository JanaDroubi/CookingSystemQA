package all;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class MealTest {

    private meal testMeal;
    private Ingredient flour;
    private Ingredient sugar;
    private Ingredient eggs;

    @BeforeEach
    void setUp() {
        // Initialize ingredients
        flour = new Ingredient("Flour", 100, 10, null);
        sugar = new Ingredient("Sugar", 50, 5, null);
        eggs = new Ingredient("Eggs", 30, 5, null);

        // Create meal with ingredients
        List<Ingredient> ingredients = Arrays.asList(flour, sugar, eggs);
        testMeal = new meal("Cake", ingredients);
        testMeal.setPrice(15.99);  // Setting a price for the meal
    }

    @Test
    void testGetName() {
        // Test the name of the meal
        assertEquals("Cake", testMeal.getName(), "Meal name should be 'Cake'");
    }

    @Test
    void testGetIngredients() {
        // Test the ingredients list
        List<Ingredient> ingredients = testMeal.getIngredients();
        assertNotNull(ingredients, "Ingredients list should not be null");
        assertEquals(3, ingredients.size(), "Meal should contain 3 ingredients");
        assertTrue(ingredients.contains(flour), "Ingredients should contain 'Flour'");
        assertTrue(ingredients.contains(sugar), "Ingredients should contain 'Sugar'");
        assertTrue(ingredients.contains(eggs), "Ingredients should contain 'Eggs'");
    }

    @Test
    void testContainsAllergenWhenPresent() {
        // Test containsAllergen method when allergen is present
        assertTrue(testMeal.containsAllergen("Sugar"), "Meal should contain 'Sugar' allergen");
    }

    @Test
    void testContainsAllergenWhenNotPresent() {
        // Test containsAllergen method when allergen is not present
        assertFalse(testMeal.containsAllergen("Milk"), "Meal should not contain 'Milk' allergen");
    }

    @Test
    void testToString() {
        // Test the toString method
        String expected = "Cake [Flour, Sugar, Eggs]";
        assertEquals(expected, testMeal.toString(), "toString method should match expected format");
    }

    @Test
    void testSetPrice() {
        // Test setting the price of the meal
        testMeal.setPrice(20.99);
        assertEquals(20.99, testMeal.getPrice(), "Meal price should be updated to 20.99");
    }

    @Test
    void testGetPrice() {
        // Test getting the price of the meal
        assertEquals(15.99, testMeal.getPrice(), "Initial price should be 15.99");
    }
}
