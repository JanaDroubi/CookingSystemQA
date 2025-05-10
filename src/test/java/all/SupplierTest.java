package all;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    private Supplier supplier;
    private Ingredient flour;
    private Ingredient sugar;

    @BeforeEach
    void setUp() {
        // Initialize the Supplier and Ingredients before each test
        supplier = new Supplier("Test Supplier");
        flour = new Ingredient("Flour", 100, 10, null);
        sugar = new Ingredient("Sugar", 50, 5, null);
    }

    @Test
    void testSetPrice() {
        // Set price for an ingredient and verify that it's stored correctly
        supplier.setPrice(flour, 2.5);
        assertEquals(2.5, supplier.getPrice(flour), "Price for flour should be 2.5");
    }

    @Test
    void testGetPriceWhenIngredientNotFound() {
        // Test behavior when the ingredient is not in the map
        assertEquals(-1.0, supplier.getPrice(sugar), "Price for sugar should be -1.0 (not found)");
    }

    @Test
    void testAddIngredientPrice() {
        // Add price using the addIngredientPrice method (which calls setPrice)
        supplier.addIngredientPrice(sugar, 1.8);
        assertEquals(1.8, supplier.getPrice(sugar), "Price for sugar should be 1.8 after being added");
    }

    @Test
    void testGetName() {
        // Verify that the name of the supplier is returned correctly
        assertEquals("Test Supplier", supplier.getName(), "Supplier name should be 'Test Supplier'");
    }

    @Test
    void testSetPriceWithNullIngredient() {
        // Test if setting a null ingredient doesn't throw an exception (based on your Supplier logic)
        assertDoesNotThrow(() -> supplier.setPrice(null, 2.5), "Setting price with null ingredient should not throw an exception");
    }

    @Test
    void testSetPriceWithNegativeValue() {
        // Test if setting a price with a negative value behaves as expected
        assertDoesNotThrow(() -> supplier.setPrice(flour, -5), "Setting negative price should not throw an exception");
        assertEquals(-5, supplier.getPrice(flour), "Price for flour should be -5 after setting negative price");
    }
}

