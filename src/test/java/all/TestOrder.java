package all;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestOrder {

    private CustomerProfile customer;
    private meal meal;
    private order order;

    @BeforeEach
    void setUp() {
        // Initialize test data
        customer = new CustomerProfile("john_doe", "password123", "customer", "vegetarian", "peanuts");
        meal = new meal("Veggie Burger", null); // Example meal with price
        order = new order(customer, meal);
    }

    @Test
    void testOrderPrice() {
        // Verify that the order price matches the meal price
        assertEquals(meal.getPrice(), order.getPrice(), "The order price should match the meal price.");
    }

    @Test
    void testGetCustomer() {
        // Verify that the correct customer is associated with the order
        assertEquals(customer, order.getCustomer(), "The customer in the order should be the same as the one assigned.");
    }

    @Test
    void testGetMeal() {
        // Verify that the correct meal is associated with the order
        assertEquals(meal, order.getMeal(), "The meal in the order should be the same as the one assigned.");
    }

    @Test
    void testToString() {
        // Verify that the toString method returns the correct order summary
        String expected = "🧑 john_doe ordered 🍽 Veggie Burger";
        assertEquals(expected, order.toString(), "The toString method should return the correct order details.");
    }
}
