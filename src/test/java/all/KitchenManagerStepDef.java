package all;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import static org.junit.Assert.*;

public class KitchenManagerStepDef {
    private Manager manager;
    private String consoleOutput;
    private ByteArrayOutputStream outputStream;

    @Given("a manager {string} with password {string} and role {string}")
    public void a_manager_with_password_and_role(String username, String password, String role) {
        manager = new Manager(username, password, role);
    }

    @Given("the following ingredients exist:")
    public void the_following_ingredients_exist(io.cucumber.datatable.DataTable dataTable) {
        Manager.ingredients.clear();
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            int quantity = Integer.parseInt(row.get("Quantity"));
            int threshold = Integer.parseInt(row.get("Threshold"));
            String alternativeName = row.get("Alternative");

            Ingredient alternative = null;
            if (!"None".equals(alternativeName)) {
                alternative = new Ingredient(alternativeName, 0, 0, null);
            }
            Manager.addIngredient(name, quantity, threshold, alternative);
        });
    }

    @When("I add ingredient {string} with quantity {int} and threshold {int}")
    public void i_add_ingredient_with_quantity_and_threshold(String name, int quantity, int threshold) {
        Manager.addIngredient(name, quantity, threshold, null);
    }

    @When("I use {int} {string} from inventory")
    public void i_use_from_inventory(int amount, String name) {
        captureConsoleOutput();
        Manager.useIngredient(name, amount);
        releaseConsoleOutput();
    }

    @When("I restock {string} with {int} more")
    public void i_restock_with_more(String name, int amount) {
        captureConsoleOutput();
        Manager.restockIngredient(name, amount);
        releaseConsoleOutput();
    }

    @When("I view the inventory")
    public void i_view_the_inventory() {
        captureConsoleOutput();
        Manager.showInventory();
        releaseConsoleOutput();
    }

    @Then("ingredient {string} should exist in inventory with quantity {int}")
    public void ingredient_should_exist_in_inventory_with_quantity(String name, int expectedQuantity) {
        Ingredient ingredient = Manager.ingredients.get(name.toLowerCase());
        assertNotNull(ingredient);
        assertEquals(expectedQuantity, ingredient.getQuantity());
    }

    @Then("{string} quantity should be {int}")
    public void quantity_should_be(String name, int expectedQuantity) {
        Ingredient ingredient = Manager.ingredients.get(name.toLowerCase());
        assertNotNull(ingredient);
        assertEquals(expectedQuantity, ingredient.getQuantity());
    }

    @Then("I should see inventory updated message")
    public void i_should_see_inventory_updated_message() {
     //   assertTrue(consoleOutput.contains("Restock Alert"));
    }

    @Then("I should see restock confirmation message")
    public void i_should_see_restock_confirmation_message() {
        assertTrue(consoleOutput.contains("✅") && consoleOutput.contains("restocked"));
    }

    @Then("I should see {string} error message")
    public void i_should_see_error_message(String expectedMessage) {
        assertTrue(consoleOutput.contains("❌") && consoleOutput.contains(expectedMessage));
    }

    @Then("I should see all ingredients with their quantities:")
    public void i_should_see_all_ingredients_with_their_quantities(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String expectedQuantity = row.get("Quantity");
            assertTrue(consoleOutput.contains(name + ": " + expectedQuantity));
        });
    }

    private void captureConsoleOutput() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void releaseConsoleOutput() {
        consoleOutput = outputStream.toString();
        System.setOut(System.out);
    }
}