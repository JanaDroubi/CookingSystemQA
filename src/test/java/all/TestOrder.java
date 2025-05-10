package all;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    private CustomerProfile customer;
    private meal meal;
    private order order;

    @Given("a customer with username {string} and a meal {string}")
    public void customer_and_meal(String username, String mealName) {
        customer = new CustomerProfile(username, "password123", "customer", "vegetarian", "peanuts");
        meal = new meal(mealName, null);
        order = new order(customer, meal);
    }

    @When("the order is created")
    public void create_order() {
        order = new order(customer, meal);
    }

    @Then("the order price should match the meal price")
    public void verify_order_price() {
        assertEquals(meal.getPrice(), order.getPrice(), "The order price should match the meal price.");
    }

    @Then("the order should belong to the customer {string}")
    public void verify_customer_in_order(String username) {
        assertEquals(customer.userName, order.getCustomer().userName, "The customer should be correctly assigned.");
    }

    @Then("the order should contain the meal {string}")
    public void verify_meal_in_order(String mealName) {
        assertEquals(meal.getName(), order.getMeal().getName(), "The meal should be correctly assigned.");
    }

    @Then("the order summary should be correct")
    public void verify_order_summary() {
        String expected = "🧑 " + customer.userName + " ordered 🍽 " + meal.getName();
        assertEquals(expected, order.toString(), "The order summary should be correctly formatted.");
    }
}