package all;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class testmanager {
    private Manager manager;

    @BeforeEach
    public void setUp() {
        manager = new Manager("admin", "password", "manager");
    }

    @Test
    public void testAddIngredient() {
        Ingredient ing = new Ingredient("Sugar", 10, 5, null);
        Manager.addIngredient("Sugar", 10, 5, ing);
        assertNotNull(Manager.ingredients.get("sugar"));
    }

    @Test
    public void testUseIngredient() {
        Ingredient ing = new Ingredient("Salt", 20, 5, null);
        Manager.addIngredient("Salt", 20, 5, ing);
        Manager.useIngredient("Salt", 5);
        assertEquals(15, Manager.ingredients.get("salt").getQuantity());

        // Attempt to use an ingredient not in the map
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manager.useIngredient("Pepper", 5);
        assertTrue(outContent.toString().contains("❌ Ingredient not found"));
    }

    @Test
    public void testRestockIngredient() {
        Manager.addIngredient("sugar", 10, 5, new Ingredient("sugar", 10, 5, null));
        Manager.restockIngredient("sugar", 5);
        Ingredient sugar = Manager.ingredients.get("sugar");
        assertNotNull(sugar, "Ingredient should exist in inventory");
        assertEquals(15, sugar.getQuantity(), "Ingredient quantity should be 15 after restocking");
    }

    @Test
    public void testShowInventory() {
        Manager.addIngredient("sugar", 10, 5, new Ingredient("sugar", 10, 5, null));
        Manager.showInventory();
        Ingredient sugar = Manager.ingredients.get("sugar");
        assertNotNull(sugar, "Ingredient should exist in inventory");
        assertEquals(10, sugar.getQuantity(), "Ingredient quantity should be 10 after adding");
    }

    @Test
    public void testIsValid() {
        assertTrue(manager.isValid());

        Manager invalidManager = new Manager(null, "password", "manager");
        assertFalse(invalidManager.isValid());
    }
}
