package all;


import all.Ingredient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class IngredientStep {
    private Ingredient currentIngredient;
    private Ingredient alternativeIngredient;
    private String alertMessage;

    @Given("I have an ingredient {string} with quantity {int} and threshold {int}")
    public void i_have_an_ingredient_with_quantity_and_threshold(String name, int quantity, int threshold) {
        currentIngredient = new Ingredient(name, quantity, threshold, null);
    }

    @Given("I have an ingredient {string} with quantity {int} and threshold {int} and alternative {string}")
    public void i_have_an_ingredient_with_quantity_threshold_and_alternative(
            String name, int quantity, int threshold, String alternativeName) {
        alternativeIngredient = new Ingredient(alternativeName, 10, 5, null);
        currentIngredient = new Ingredient(name, quantity, threshold, alternativeIngredient);
    }

    @When("I check if {string} is low stock")
    public void i_check_if_is_low_stock(String name) {
        // The check is implicit in the assertion step
    }

    @Then("it should not be low stock")
    public void it_should_not_be_low_stock() {
        assertFalse(currentIngredient.isLowStock());
    }

    @Then("it should be low stock")
    public void it_should_be_low_stock() {
        assertTrue(currentIngredient.isLowStock());
    }

    @When("I reduce {string} quantity by {int}")
    public void i_reduce_quantity_by(String name, int amount) {
        // Capture System.out messages for alert testing
        System.setOut(new java.io.PrintStream(System.out) {
            public void println(String msg) {
                alertMessage = msg;
                super.println(msg);
            }
        });
        currentIngredient.reduceQuantity(amount);
        System.setOut(System.out);
    }

    @Then("the remaining quantity should be {int}")
    public void the_remaining_quantity_should_be(int expectedQuantity) {
        assertEquals(expectedQuantity, currentIngredient.getQuantity());
    }

    @Then("I should see a restock alert for {string}")
    public void i_should_see_a_restock_alert_for(String name) {
        assertNotNull(alertMessage);
        assertTrue(alertMessage.contains("Restock Alert: " + name));
        assertTrue(alertMessage.contains("is low"));
    }

    @When("I restock {string} with {int}")
    public void i_restock_with(String name, int amount) {
        currentIngredient.restock(amount);
    }

    @Then("the new quantity should be {int}")
    public void the_new_quantity_should_be(int expectedQuantity) {
        assertEquals(expectedQuantity, currentIngredient.getQuantity());
    }

    @When("I set {string} as alternative for {string}")
    public void i_set_as_alternative_for(String alternativeName, String ingredientName) {
        alternativeIngredient = new Ingredient(alternativeName, 10, 5, null);
        currentIngredient = new Ingredient(ingredientName, 5, 3, alternativeIngredient);
    }

    @Then("the alternative for {string} should be {string}")
    public void the_alternative_for_should_be(String ingredientName, String expectedAlternative) {
        Ingredient alternative = currentIngredient.getAlternative();
        assertNotNull(alternative);
        assertEquals(expectedAlternative, alternative.getName());
    }


}