package all;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    private Ingredient ingredient;
    private Ingredient alternative;

    @BeforeEach
    public void setUp() {
        alternative = new Ingredient("Salt", 50, 10, null);
        ingredient = new Ingredient("Sugar", 100, 20, alternative);
    }

    @Test
    public void testIsLowStock() {
        assertFalse(ingredient.isLowStock());
        ingredient.reduceQuantity(85);
        assertTrue(ingredient.isLowStock());
    }

    @Test
    public void testReduceQuantity() {
        ingredient.reduceQuantity(30);
        assertEquals(70, ingredient.getQuantity());
    }

    @Test
    public void testIncreaseQuantity() {
        ingredient.IncreaseQuantity(20);
        assertEquals(120, ingredient.getQuantity());
    }

    @Test
    public void testRestock() {
        ingredient.restock(50);
        assertEquals(150, ingredient.getQuantity());
    }

    @Test
    public void testGetAlternative() {
        assertEquals(alternative, ingredient.getAlternative());
    }

    @Test
    public void testEquals() {
        Ingredient anotherIngredient = new Ingredient("sugar", 200, 15, null);
        assertTrue(ingredient.equals(anotherIngredient));
    }

    @Test
    public void testNotEquals() {
        Ingredient anotherIngredient = new Ingredient("Flour", 200, 15, null);
        assertFalse(ingredient.equals(anotherIngredient));
    }

}
