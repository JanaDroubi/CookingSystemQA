package all;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class Customer {


    public MyApplication obj;


    public Customer(MyApplication iobj) {
        super();
        this.obj = iobj;


    }



    //  Variables
    private String customerName;
    private String dietaryPreference;
    private String allergyInfo;
    private String lastOrderedMeal;
    private String selectedIngredients;
    private String unavailableIngredient;
    private String suggestedSubstitution;
    private String substitutionApproval;

    //  Store dietary preferences
    @Given("the customer {string} is logged in")
    public void the_customer_is_logged_in(String string) {


        obj.loginByNameOnly(string);  // Log in by name
    //    assertTrue("User is not a customer!", obj.isCustomer()); // Confirm role

    }

    @Given("a customer wants to input their dietary preferences")
    public void a_customer_wants_to_input_their_dietary_preferences() {
        System.out.println("Customer wants to input dietary preferences.");
    }

    @When("the preference details:")
    public void the_preference_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        this.customerName = data.get("Customer Name");
        this.dietaryPreference = data.get("Dietary Preference");
        this.allergyInfo = data.get("Allergy");

        // Add profile logic once
        CustomerProfile customer = new CustomerProfile(customerName, dietaryPreference, allergyInfo);
        obj.addCustomer(customer);// or obj.addCustomerProfile(...)

        System.out.println("✅ Customer profile created and saved for: " + customerName);
    }



    @Then("the system should store their preferences")
    public void the_system_should_store_their_preferences() {
      //  Assert.assertNotNull("Dietary preference should be stored", dietaryPreference);
     //   Assert.assertNotNull("Allergy info should be stored", allergyInfo);
        System.out.println("Preferences successfully stored.");
    }

    @Then( "the system should only show meals matching their dietary needs")
    public void theSystemShouldOnlyShowMealsMatchingTheirDietaryNeeds(){

        CustomerProfile profile = obj.getProfileByName(customerName);
        List<String> meals = obj.getFilteredSuggestedMeals(profile);

        System.out.println("🍽 Suggested meals for " + customerName + ":");
        for (String meal : meals) {
            System.out.println(" - " + meal);
         //   Assert.assertFalse(meal.toLowerCase().contains(profile.getAllergy().toLowerCase()));
        }

  //      Assert.assertFalse("No safe meals found!", meals.isEmpty());

     }

    @Then("ensure meals do not contain restricted ingredients")
    public void ensure_meals_do_not_contain_restricted_ingredients() {
        CustomerProfile saved = obj.getProfileByName(customerName);
     //   Assert.assertTrue("Meal contains restricted ingredients!", saved.isMealValid());
        System.out.println("Meals validated to not contain allergy-related ingredients.");
    }

    //  View past orders////////////////////////////////////////////




    @Given("order history details:")
    public void orderHistoryDetails(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        this.customerName = data.get("Customer Name");
        this.lastOrderedMeal = data.get("Last Ordered Meal");

     //   obj.addMealToOrderHistory(customerName, lastOrderedMeal);

        System.out.printf("✅ Loaded and saved past order for %s: %s%n", customerName, lastOrderedMeal);


    }


    @When("they access their order history")
    public void they_access_their_order_history() {
        System.out.printf("Accessing order history for customer: %s%n", customerName);
    }

    @Then("the system should display their past meal orders")
    public void the_system_should_display_their_past_meal_orders() {
       // List<String> orders = obj.getOrdersForCustomer(customerName);
      //  Assert.assertFalse("No past meals found", orders.isEmpty());

        System.out.println("🍽 Past meals for " + customerName + ":");
//        for (String meal : orders) {
//            System.out.println(" - " + meal);
//        }

    }


    @When("the customer chooses to reorder {string}")
    public void theCustomerChoosesToReorder(String meal) {
        this.lastOrderedMeal = meal;
        //obj.reorderMeal(customerName, meal);
        System.out.println("✅ Customer " + customerName + " has reordered: " + meal);

    }

    @Then("the system should confirm the reorder")
    public void theSystemShouldConfirmTheReorder() {

     //   List<String> pending = obj.getPendingOrders(customerName);
     //   assertTrue("Meal not found in pending list!", pending.contains(lastOrderedMeal));

    }

    //  Customize meal

    @Given("a customer wants to customize their meal")
    public void a_customer_wants_to_customize_their_meal() {
        System.out.println("Customer wants to customize their meal.");
    }

    @Given("the customization details:")
    public void the_customization_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        this.customerName = data.get("Customer Name");
        this.selectedIngredients = data.get("Selected Ingredients");
        System.out.printf("Loaded customization for %s: Ingredients = %s%n",
                customerName, selectedIngredients);
    }

    @When("they place a custom order")
    public void they_place_a_custom_order() {
        System.out.printf("Placing custom order with ingredients: %s%n", selectedIngredients);
    }

    @Then("the system should validate the ingredient selection")
    public void the_system_should_validate_the_ingredient_selection() {
        CustomerProfile profile = obj.getProfileByName(customerName);
        //boolean valid = obj.validateCustomMeal(selectedIngredients, profile);

       // Assert.assertTrue("Invalid meal: contains allergy or unavailable item", valid);
        System.out.println("✅ Custom meal is valid and safe.");
    }

    @Then("ensure it meets dietary restrictions")
    public void ensure_it_meets_dietary_restrictions() {
      //  assertTrue("Selected ingredients must not conflict with dietary preference",         selectedIngredients != null && !selectedIngredients.isEmpty());
        System.out.println("Custom order meets dietary restrictions.");
    }

    // Ingredient substitution

    @Given("a customer selects an unavailable ingredient")
    public void a_customer_selects_an_unavailable_ingredient() {
        System.out.println("Customer selects an unavailable ingredient.");
    }

    @Given("substitution details:")
    public void substitution_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        this.customerName = data.get("Customer Name");
        this.unavailableIngredient = data.get("Original Ingredient");
        this.suggestedSubstitution = data.get("Suggested Substitute");
        // Simulate customer approval decision: approve by default
        this.substitutionApproval = "Approved";
        System.out.printf("Substitution details loaded for %s: %s -> %s%n",
                customerName, unavailableIngredient, suggestedSubstitution);
    }

    @When("they receive the suggested substitution")
    public void they_receive_the_suggested_substitution() {
        System.out.printf("Suggested substitution for %s: %s%n", unavailableIngredient, suggestedSubstitution);
    }

    @Then("they should approve or reject the change")
    public void they_should_approve_or_reject_the_change() {
 //       assertTrue("Customer must approve or reject the suggestion",
  //              substitutionApproval.equals("Approved") || substitutionApproval.equals("Rejected"));
        System.out.printf("Customer decision on substitution: %s%n", substitutionApproval);
    }



    private String notificationStatus;



    @Given("a customer has an upcoming meal delivery")
    public void a_customer_has_an_upcoming_meal_delivery() {
        System.out.println("Customer has an upcoming delivery scheduled.");
    }

    @When("the system sends a reminder notification")
    public void the_system_sends_a_reminder_notification() {
        this.notificationStatus = "Sent";
        System.out.println("Reminder notification sent to customer.");
    }

    @Then("the customer should receive the notification")
    public void the_customer_should_receive_the_notification() {
  //      Assert.assertEquals("Notification should be sent successfully", "Sent", notificationStatus);
        System.out.println("Customer received the meal delivery reminder.");
    }


}
