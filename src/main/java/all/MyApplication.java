package all;

import java.util.*;
import java.util.stream.Collectors;

public class MyApplication {



    ///////////////log in/////////////////////////////
    //private final List<Person> users;
    public static List<chef> chefs = new ArrayList<>(); // array of ches
    public static List<Manager> managers = new ArrayList<>(); // array of managers
    public static List<Ingredient> ingredients = new ArrayList<>(); // array of ingredients
    public static List<Supplier> suppliers = new ArrayList<>();
    private static List<CustomerProfile> customers = new ArrayList<>();// array of suppliers
    private static final List<order> pendingOrders = new ArrayList<>();
     private  static final Map<CustomerProfile, List<order>> orderHistory = new HashMap<>();
    private static final List<order> allOrders = new ArrayList<>();
    private static final List<meal> meals=new ArrayList<>();
    public static Manager testmanager = new Manager("test","test","test");
    private final List<String> notificationLog = new ArrayList<>();

    private String message;
    private boolean validation;
    private boolean Customerlogged;
    private Person loggedInUser;
    private boolean isLoggedIn;

    public MyApplication() {
       // users = new ArrayList<>();
        // mock users

        CustomerProfile alice = new CustomerProfile("Alice", "123", "customer", "Vegetarian", "Nuts");
        CustomerProfile mark  = new CustomerProfile("Mark", "1234", "customer", "Vegan", "Dairy");
        CustomerProfile emily = new CustomerProfile("Emily", "1234", "customer", "Vegetarian", "None");
        CustomerProfile tom   = new CustomerProfile("Tom", "1234", "customer", "Low Carb", "Gluten");
        CustomerProfile jake  = new CustomerProfile("Jake", "1234", "customer", "High Protein", "Eggs");





            customers.add(alice);
            customers.add(mark);
            customers.add(emily);
            customers.add(tom);
            customers.add(jake);





        chefs.add(new chef("chef1", "grilling", "chef1pass", "chef"));
        chefs.add(new chef("chef2", "vegan", "chef2pass", "chef"));
        chefs.add(new chef("chef3", "baking", "chef3pass", "chef"));

        managers.add(new Manager("manager1", "manager1pass", "manager"));
        managers.add(new Manager("manager2", "manager2pass", "manager"));
        managers.add(new Manager("manager3", "manager3pass", "manager"));


        // 🥦 Mock ingredients
        Ingredient tomato = new Ingredient("Tomato", 20, 10, new Ingredient("Red Pepper", 10, 5, null));
        Ingredient cheese = new Ingredient("Cheese", 5, 8, new Ingredient("Vegan Cheese", 10, 5, null));
        Ingredient lettuce = new Ingredient("Lettuce", 2, 5, new Ingredient("Spinach", 10, 5, null));
        Ingredient onion = new Ingredient("Onion", 15, 10, new Ingredient("Leek", 7, 4, null));
        Ingredient garlic = new Ingredient("Garlic", 7, 5, null);
        Ingredient beef = new Ingredient("Beef", 3, 6, new Ingredient("Tofu", 15, 5, null));
        Ingredient chicken = new Ingredient("Chicken", 12, 8, new Ingredient("Soy Chunks", 10, 5, null));
        Ingredient flour = new Ingredient("Flour", 25, 15, new Ingredient("Oat Flour", 10, 5, null));
        Ingredient sugar = new Ingredient("Sugar", 18, 10, new Ingredient("Stevia", 8, 3, null));
        Ingredient salt = new Ingredient("Salt", 20, 10, null);
        Ingredient tofu = new Ingredient("Tofu", 15, 5, new Ingredient("Tempeh", 10, 5, null));

        ingredients.add(tomato);  ingredients.add(cheese);  ingredients.add(lettuce);  ingredients.add(onion);
        ingredients.add(garlic);  ingredients.add(beef);  ingredients.add(chicken);  ingredients.add(flour);
        ingredients.add(sugar);  ingredients.add(tofu);  ingredients.add(salt);
        Ingredient OatMilk =new Ingredient("Oat Milk", 10, 5, null);

        // Ingredients for Vegan, Vegetarian, and High Protein diets, with alternatives
        Ingredient spinach = new Ingredient("Spinach", 15, 5, new Ingredient("Kale", 10, 5, null));
        Ingredient avocado = new Ingredient("Avocado", 8, 3, new Ingredient("Hummus", 10, 5, null));
        Ingredient quinoa = new Ingredient("Quinoa", 20, 10, new Ingredient("Brown Rice", 15, 5, null));
        Ingredient chickpeas = new Ingredient("Chickpeas", 25, 10, new Ingredient("Lentils", 20, 10, null));
        Ingredient almondMilk = new Ingredient("Almond Milk", 12, 5, OatMilk);
        ingredients.add(OatMilk);

        Ingredient stevia = new Ingredient("Stevia", 18, 5, new Ingredient("Maple Syrup", 10, 5, null));
        Ingredient broccoli = new Ingredient("Broccoli", 20, 8, new Ingredient("Cauliflower", 15, 5, null));
        Ingredient oliveOil = new Ingredient("Olive Oil", 30, 10, new Ingredient("Coconut Oil", 15, 5, null));
        ingredients.add(spinach); ingredients.add(avocado); ingredients.add(quinoa); ingredients.add(chickpeas);
        ingredients.add(almondMilk); ingredients.add(stevia); ingredients.add(broccoli); ingredients.add(oliveOil);



        meal veganBowl = new meal("Vegan Bowl", List.of(tofu, lettuce, tomato), "Vegan");
        meal beefBurger = new meal("Beef Burger", List.of(beef, onion, lettuce, salt), "High Protein");
        meal cheesyGarlicBread = new meal("Cheesy Garlic Bread", List.of(flour, cheese, garlic), "Vegetarian");
        meal chickenWrap = new meal("Chicken Wrap", List.of(chicken, tomato, lettuce, onion), "High Protein");
        meal sweetBites = new meal("Sweet Bites", List.of(sugar, flour), "Vegetarian");
        meal proteinDelight = new meal("Protein Delight", List.of(beef, chicken, garlic), "High Protein");
        meal greenSalad = new meal("Green Salad", List.of(lettuce, tomato, onion), "Vegan");
        meal classicToast = new meal("Classic Toast", List.of(flour, salt), "Vegetarian");
        meal dietSmoothie = new meal("Diet Smoothie", List.of(sugar, salt, tomato), "Vegan");




        meals.add(veganBowl);
        meals.add(beefBurger);
        meals.add(cheesyGarlicBread);
        meals.add(chickenWrap);
        meals.add(sweetBites);
        meals.add(proteinDelight);
        meals.add(greenSalad);
        meals.add(classicToast);
        meals.add(dietSmoothie);


        meal fruitBowl = new meal("Fruit Bowl", List.of(tomato, sugar), "Vegan"); // Simplified ingredients
        meal lentilSoup = new meal("Lentil Soup", List.of(onion, tomato), "Vegan");
        meal glutenFreePasta = new meal("Gluten-Free Pasta", List.of(tofu, tomato), "Vegan");
        meal proteinShake = new meal("Protein Shake", List.of(chicken, sugar), "High Protein");

        // Vegan meals
        meal quinoaAvocadoBowl = new meal("Quinoa Avocado Bowl", List.of(quinoa, avocado, spinach, oliveOil), "Vegan");
        meal chickpeaStirFry = new meal("Chickpea Stir Fry", List.of(chickpeas, broccoli, garlic, oliveOil), "Vegan");

// Vegetarian meals
        meal spinachSmoothie = new meal("Spinach Smoothie", List.of(spinach, almondMilk, stevia), "Vegetarian");

// High Protein meals
        meal tofuQuinoaSalad = new meal("Tofu Quinoa Salad", List.of(tofu, quinoa, broccoli, oliveOil), "High Protein");

        meals.add(fruitBowl);
        meals.add(lentilSoup);
        meals.add(glutenFreePasta);
        meals.add(proteinShake);
        meal grilledChickenSalad = new meal("Grilled Chicken Salad", List.of(chicken, lettuce, tomato), "High Protein");
        meals.add(grilledChickenSalad);
        meals.add(quinoaAvocadoBowl);
        meals.add(spinachSmoothie);
        meals.add(chickpeaStirFry);
        meals.add(tofuQuinoaSalad);




        // 🚚 Mock suppliers
        Supplier supplier1 = new Supplier("FreshFoods");
        supplier1.addIngredientPrice(tomato, 2.0);
        supplier1.addIngredientPrice(cheese, 5.5);
        supplier1.addIngredientPrice(garlic, 1.0);
        supplier1.addIngredientPrice(beef, 10.0);

        Supplier supplier2 = new Supplier("GreenHarvest");
        supplier2.addIngredientPrice(lettuce, 1.2);
        supplier2.addIngredientPrice(onion, 1.8);
        supplier2.addIngredientPrice(chicken,6.5);

        Supplier supplier3 = new Supplier("DailyEssentials");
        supplier3.addIngredientPrice(flour,0.9);
        supplier3.addIngredientPrice(sugar, 1.1);
        supplier3.addIngredientPrice(salt, 0.5);

        suppliers.add(supplier1);
        suppliers.add(supplier2);
        suppliers.add(supplier3);



        Admin admin = new Admin("admin1", "adminpass");


        isLoggedIn = false;

    }

//
//    public Map<String, List<String>> getOrderHistoryMap() {
//        return orderHistory;
//    }

    public void setUsernameAndPassAndPassFromSystem(String name, String pass) {
        validation = false;

        message = "";

        if (name.isEmpty() && pass.isEmpty()) {
            message = "Username and password cannot be empty";
            return ;
        }

        if (name.isEmpty()) {
            message = "Username cannot be empty";
            return;
        }

        if (pass.isEmpty()) {
            message = "Password cannot be empty";
            return;
        }

        for (chef chef : chefs) {
            if (chef.getUserName().equals(name)) {
                if (chef.getPass().equals(pass)) {
                    validation = true;
                    loggedInUser = chef;
                    message = "Chef Found";
                    return;
                } else {
                    message = "Incorrect password";
                    return;
                }
            }
        }

// Then check managers
        for (Manager manager : managers) {
            if (manager.getUserName().equals(name)) {
                if (manager.getPass().equals(pass)) {
                    validation = true;
                    loggedInUser = manager;
                    message = "Manager Found";
                    return;
                } else {
                    message = "Incorrect password";
                    return;
                }
            }
        }

// Finally check customers
        for (CustomerProfile customer : customers) {
            if (customer.getUserName().equals(name)) {
                if (customer.getPass().equals(pass)) {
                    validation = true;
                    loggedInUser = customer;
                    message = "Customer Found";
                    return;
                } else {
                    message = "Incorrect password";
                    return;
                }
            }
        }


        message = "User not found";
    }

    public String getLoggedInUserRole() {
        return (loggedInUser != null) ? loggedInUser.getRole() : null;
    }

    public boolean getValidation() {
        return validation;
    }

    public void iAmNotInSystem(MyApplication obj) {
        validation = false;
        loggedInUser = null;
    }

    public String getMessage() {
        return message;
    }

////////////////////////////////////////////////////////////////////////

    public boolean isCustomer() {

        return loggedInUser != null && "customer".equalsIgnoreCase(loggedInUser.getRole());
    }

    public void loginByNameOnly(String name) {

            for (CustomerProfile c : customers) {
                if (c.getUserName().equalsIgnoreCase(name)) {
                    loggedInUser = c;
                    System.out.println("🔐 User logged in by name: " + name);
                    return;
                }
            }
            System.out.println("❌ No customer found with name: " + name);
        }


    public List<CustomerProfile> getCustomerProfiles() {
        return customers;
    }

    public void addCustomer(CustomerProfile c) {
        if (c != null && c.isValid()) {
            customers.add(c);
            System.out.println("✅ Customer added: " + c.getUserName());
        } else {
            System.out.println("❌ Invalid customer object.");
        }
    }

    public void addChef(chef c) {
        if (c != null && c.isValid()) {
            chefs.add(c);
            System.out.println("✅ Customer added: " + c.getUserName());
        } else {
            System.out.println("❌ Invalid customer object.");
        }
    }
    public void addManager(Manager c) {
        if (c != null && c.isValid()) {
            managers.add(c);
            System.out.println("✅ Customer added: " + c.getUserName());
        } else {
            System.out.println("❌ Invalid customer object.");
        }
    }

    public CustomerProfile getProfileByName(String name) {
        for (CustomerProfile profile : customers) {
            if (profile.getUserName().equalsIgnoreCase(name)) {
                return profile;
            }
        }
        return null;
    }


    private List<String> suggestedMeals = Arrays.asList(
            "Mushroom Risotto",
            "Almond Milk Smoothie",
            "Lentil Stew",
            "Vegan Tofu Stir-Fry",
            "Grilled Chicken"
    );

//    public List<meal> getFilteredSuggestedMeals(CustomerProfile profile) {
//        return meals.stream()
//                .filter(m -> !m.containsAllergen(profile.getAllergy()))
//                .filter(m -> m.getDietaryCategory().equalsIgnoreCase(profile.getDietaryPreference()))
//                .collect(Collectors.toList());
//    }


    ////////////////////orders////////////////////////////


   // private Map<String, List<String>> pendingOrders = new HashMap<>();


//    public void addToPendingOrders( CustomerProfile b, meal m) {
//        pendingOrders.add(new order(b, m));
//        System.out.println("⚠️ Order added to pending list. Please confirm it before submission.");
//    }

    public meal getMealByName(String mealName) {
        for (meal m : meals) {
            if (m.getName().equalsIgnoreCase(mealName)) {
                return m;
            }
        }
        return null;
    }


//    public List<order> getPendingOrdersForCustomer(CustomerProfile customer) {
//        return pendingOrders.stream()
//                .filter(order -> order.getCustomer().equals(customer))
//                .toList();
//    }
    public Map<CustomerProfile, List<order>>  getOrdersForCustomer(CustomerProfile customer) {
        return orderHistory;
    }






    ///////////////////////////////////history////////////////////////////
//    private Map<String, List<String>> orderHistory = new HashMap<>();


//    public void addMealToOrderHistory(String customerName, String meal) {
//        orderHistory.putIfAbsent(customerName, new ArrayList<>());
//        orderHistory.get(customerName).add(meal);
//    }
//
//    public void reorderMeal(String customerName, String meal) {
//        pendingOrders.putIfAbsent(customerName, new ArrayList<>());
//        pendingOrders.get(customerName).add(meal);
//
//        System.out.println("⚠️ '" + meal + "' has been added to your pending orders.");
//        System.out.println("Please confirm your order to send it to the chef.");
//
//    }
//
//    public List<String> getOrdersForCustomer(String customerName) {
//        return orderHistory.getOrDefault(customerName, new ArrayList<>());
//
//
//    }
//
//
//    private Map<String, List<String>> allOrders = new HashMap<>();
//
//    public void addOrder(String customerName, String meal) {
//
//        allOrders.putIfAbsent(customerName, new ArrayList<>());
//
//        allOrders.get(customerName).add(meal);
//    }

/////////////////// kitchen manager ////////////////////

    public static void assignTaskToChef(String task, String requiredExpertise) {
        chef bestChef = null;

        for (chef chef : chefs) {
            if (chef.getExpertise().equalsIgnoreCase(requiredExpertise)) {
                if (bestChef == null || chef.getTaskCount() < bestChef.getTaskCount()) {
                    bestChef = chef;
                }
            }
        }

        if (bestChef != null) {
            bestChef.assignTask(task);
        } else {
            System.out.println("❌ No chef available with expertise: " + requiredExpertise);
        }
    }

///////////////////////////////////////////////////////////////
    public static void viewAssignedTasksForChef(String chefName) {
        for (chef chef : chefs) {
            if (chef.getUserName().equalsIgnoreCase(chefName)) {
                System.out.println("📋 Tasks for " + chef.getUserName() + ":");
                for (String task : chef.getAssignedTasks()) {
                    System.out.println(" - " + task);
                }
                return;
            }
        }
        System.out.println("❌ Chef not found.");
    }

    ////////////////////////////////////////////////////////////////////////////
    private Set<String> unavailableIngredients = Set.of("Peanuts", "Shellfish", "Bacon");



//    public boolean validateCustomMeal(String selectedIngredients, CustomerProfile profile) {
//
//        String[] selected = ingredients.Split(",\\s*");
//        for (String ing : selected) {
//            // 1. Check allergy
//            if (profile.getAllergy().equalsIgnoreCase(ing)) {
//                System.out.println("❌ Ingredient conflicts with allergy: " + ing);
//                return false;
//            }
//            // 2. Check stock
//            if (unavailableIngredients.contains(ing)) {
//           System.out.println("⚠️ Ingredient unavailable: " + ing);
//                return false;
//            }
//        }
//        return true;
//
//
//    }


    public double getPriceForIngredient(Ingredient ingredient) {
        for (Supplier supplier : suppliers) {
            double price = supplier.getPrice(ingredient);
            if (price >= 0) {
                return price;
            }
        }
        System.out.println("⚠️ No supplier found for ingredient: " + ingredient.getName());
        return 0.0;  // or -1.0 if you want to flag it as an error
    }

    public double calculateMealPrice(List<Ingredient> ingredients) {
        double price = 0.0;
        for (Ingredient ing : ingredients) {

         //   price += ingredientPrices.getOrDefault(ing, 0.0); // assuming ingredientPrices is a Map
        }
        return price;
    }


//    public List<Ingredient> validateIngredients(List<Ingredient> selected, CustomerProfile customer) {
//        List<Ingredient> finalList = new ArrayList<>();
//
//        for (Ingredient ing : selected) {
//            boolean unavailable = ing.getQuantity() < ing.getThreshold();
//            boolean allergic = ing.getName().equalsIgnoreCase(customer.getAllergy());
//
//            if (unavailable || allergic) {
//                if (ing.getAlternative() != null) {
//                    System.out.printf("⚠️ '%s' is %s. Suggested: %s%n",
//                            ing.getName(),
//                            allergic ? "an allergen" : "out of stock",
//                            ing.getAlternative().getName());
//
//                    alertChef(customer, ing, ing.getAlternative());
//                    finalList.add(ing.getAlternative());  // apply substitution
//                } else {
//                    System.out.printf("❌ No substitute available for '%s'. Removing it.%n", ing.getName());
//                }
//            } else {
//                finalList.add(ing);
//            }
//        }
//
//        return finalList;
//    }

    private void alertChef(CustomerProfile customer, Ingredient original, Ingredient substitute) {
        System.out.printf("👨‍🍳 Chef Alert: %s's order substituted %s with %s.%n", customer.getUserName(), original.getName(), substitute.getName());

    }

    public void showAllAvailableMeals(CustomerProfile customer) {
        if (meals.isEmpty()) {
            System.out.println("❌ No meals available.");
            return;
        }

        System.out.println("\n🍽️ Meals safe for " + customer.getUserName() + " (Allergy: " + customer.getAllergy() + "):");

        int count = 0;
        for (int i = 0; i < meals.size(); i++) {
            meal meal = meals.get(i);

            // ✅ Skip meals that contain allergens
            if (meal.containsAllergen(customer.getAllergy())) {
                continue;
            }

            double price = calculateMealPrice(meal.getIngredients());
            System.out.printf("%d. %s - $%.2f\n", ++count, meal.getName(), price);

            System.out.print("   Ingredients: ");
            for (int j = 0; j < meal.getIngredients().size(); j++) {
                Ingredient ing = meal.getIngredients().get(j);
                System.out.print(ing.getName());
                if (j < meal.getIngredients().size() - 1) System.out.print(", ");
            }
            System.out.println(); // new line
        }

        if (count == 0) {
            System.out.println("⚠️ No meals match your allergy restrictions.");
        }
    }

//    public void chefViewOrderHistory() {
//        if (orderHistory.isEmpty()) {
//            System.out.println("No customer order history available.");
//            return;
//        }
//
//       List<CustomerProfile> customers = new ArrayList<>(orderHistory.keySet());
//        System.out.println("👤 Customers with orders:");
//        for (int i = 0; i < customers.size(); i++) {
//            System.out.printf("%d. %s\n", i + 1, customers.get(i).getName());
//        }
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Choose a customer to view their history: ");
//        int choice = scanner.nextInt();
//
//        if (choice > 0 && choice <= customers.size()) {
//            CustomerProfile selected = customers.get(choice - 1);
//            List<meal> history = orderHistory.get(selected);
//            System.out.println("📦 Order History for " + selected.getName() + ":");
//           for (meal meal : history) {
//                System.out.println(" - " + meal);
//           }
//        } else {
//            System.out.println("Invalid choice.");
//        }
//    }
//






    public void viewChefTasks(String username) {
        chef ch = chefs.get(Integer.parseInt(username));
        if (ch != null) {
            List<String> tasks = ch.getAssignedTasks();
            if (tasks.isEmpty()) {
                System.out.println("📋 No tasks assigned.");
            } else {
                System.out.println("📋 Your assigned tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        }
    }

    public void completeChefTask(String username, int taskIndex) {
        chef ch = chefs.get(Integer.parseInt(username));
        if (ch != null) {
            List<String> tasks = ch.getAssignedTasks();
            if (taskIndex > 0 && taskIndex <= tasks.size()) {
                String completedTask = tasks.remove(taskIndex - 1);
                System.out.println("✅ Task completed: " + completedTask);
            } else {
                System.out.println("❌ Invalid task number.");
            }
        }
    }


    public List<chef> getChefs() {
        return chefs;
    }

    public void setChefs(List<chef> chefs) {
        this.chefs = chefs;
    }


    public Ingredient findalternative (String name ) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                    return ingredient;
                } else {
                    message = "Incorrect password";
                    return null;
                }
    }
        return null;
}

    public void useIngredient(String name, int qty) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                ingredient.reduceQuantity(qty);
            } else {
                message = "Ingredient not found";
            }
        }

    }

    public void restockIngredient(String name, int qty) {       for (Ingredient ingredient : ingredients) {
        if (ingredient.getName().equals(name)) {
            ingredient.IncreaseQuantity(qty);
        } else {
            message = "Ingredient not found";
        }
    }
    }
    //////////////////////////////////////////////////////


    public void displayCustomerDietaryInfo(CustomerProfile customer) {
        if (customer == null) {
            System.out.println("❌ Customer not found.");
            return;
        }

        System.out.println("📋 Dietary Profile for " + customer.getUserName() + ":");
        System.out.println("   • Preference: " + customer.getDietaryPreference());
        System.out.println("   • Allergy   : " + customer.getAllergy());
    }




//
//    public void addMealToOrderHistory(CustomerProfile customer, String mealName) {
//        meal matchedMeal = meals.stream()
//                .filter(m -> m.getName().equalsIgnoreCase(mealName))
//                .findFirst()
//                .orElse(null);
//
//        if (matchedMeal == null) {
//            System.out.println("⚠️ Meal not found: " + mealName);
//            return;
//        }
//
//        orderHistory.putIfAbsent(customer, new ArrayList<>());
//        orderHistory.get(customer).add(new order(customer, matchedMeal));
//
//        System.out.printf("✅ Order added to %s's history: %s\n", customer.getUserName(), mealName);
//    }



    public static class ValidationResult {
        private final List<Ingredient> validatedIngredients;
        private final List<Substitution> substitutions;

        public static class Substitution {
            public final Ingredient original;
            public final Ingredient substitute;
            public final String reason;

            public Substitution(Ingredient original, Ingredient substitute, String reason) {
                this.original = original;
                this.substitute = substitute;
                this.reason = reason;
            }
        }

        public ValidationResult(List<Ingredient> validatedIngredients, List<Substitution> substitutions) {
            this.validatedIngredients = validatedIngredients;
            this.substitutions = substitutions;
        }

        public List<Ingredient> getValidatedIngredients() {
            return validatedIngredients;
        }

        public List<Substitution> getSubstitutions() {
            return substitutions;
        }
    }

    public ValidationResult validateIngredients(List<Ingredient> selected, CustomerProfile customer) {
        List<Ingredient> finalList = new ArrayList<>();
        List<ValidationResult.Substitution> substitutions = new ArrayList<>();

        for (Ingredient ing : selected) {
            boolean unavailable = ing.getQuantity() < ing.getThreshold();
            boolean allergic = ing.getName().equalsIgnoreCase(customer.getAllergy());

            if (unavailable || allergic) {
                if (ing.getAlternative() != null) {
                    String reason = allergic ? "allergen" : "out of stock";
                    substitutions.add(new ValidationResult.Substitution(ing, ing.getAlternative(), reason));
                    alertChef(customer, ing, ing.getAlternative());
                    finalList.add(ing.getAlternative());
                } else {
                    System.out.printf("❌ No substitute available for '%s'. Removing it.%n", ing.getName());
                }
            } else {
                finalList.add(ing);
            }
        }

        return new ValidationResult(finalList, substitutions);
    }

    // New: Validate a custom meal based on a comma-separated ingredient string
    public ValidationResult validateCustomMeal(String ingredientList, CustomerProfile profile) {
        List<Ingredient> selectedIngredients = Arrays.stream(ingredientList.split(",\\s*"))
                .map(this::findIngredient)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (selectedIngredients.isEmpty()) {
            System.out.println("❌ No valid ingredients found in: " + ingredientList);
            return new ValidationResult(List.of(), List.of());
        }

        // Validate dietary preference (e.g., ensure all ingredients are compatible)
        boolean dietaryMismatch = selectedIngredients.stream()
                .anyMatch(ing -> !isIngredientCompatibleWithDietaryPreference(ing, profile.getDietaryPreference()));
        if (dietaryMismatch) {
            System.out.println("❌ Ingredients do not match dietary preference: " + profile.getDietaryPreference());
            return new ValidationResult(List.of(), List.of());
        }

        // Use existing validateIngredients for allergy and stock checks
        return validateIngredients(selectedIngredients, profile);
    }

    // Helper for validateCustomMeal
    private Ingredient findIngredient(String name) {
        return ingredients.stream()
                .filter(ing -> ing.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Helper for dietary preference check (simplified, can be expanded)
    private boolean isIngredientCompatibleWithDietaryPreference(Ingredient ingredient, String dietaryPreference) {
        // Example logic: reject non-vegan ingredients for vegan preference
        if (dietaryPreference.equalsIgnoreCase("Vegan")) {
            return !List.of("Beef", "Chicken", "Cheese").contains(ingredient.getName());
        } else if (dietaryPreference.equalsIgnoreCase("Vegetarian")) {
            return !List.of("Beef", "Chicken").contains(ingredient.getName());
        }
        return true; // Default: assume compatible for other preferences
    }

    // New: Handle ingredient substitution with customer approval
    public boolean handleIngredientSubstitution(Ingredient original, Ingredient substitute, CustomerProfile customer, boolean approved) {
        if (!approved) {
            System.out.printf("❌ Customer %s rejected substitution: %s -> %s%n", customer.getUserName(), original.getName(), substitute.getName());
            return false;
        }

        // Simulate updating the order with the substitute
        System.out.printf("✅ Customer %s approved substitution: %s -> %s%n", customer.getUserName(), original.getName(), substitute.getName());
        // Could update pendingOrders or create a new order with the substitute
        return true;
    }

    // New: Get order history for a specific customer
    public List<order> getCustomerOrderHistory(CustomerProfile customer) {
        return orderHistory.getOrDefault(customer, new ArrayList<>());
    }

    // New: Send a notification (mock implementation)
    public void sendNotification(CustomerProfile customer, String message) {
        String notification = String.format("Notification to %s: %s", customer.getUserName(), message);
        notificationLog.add(notification);
        System.out.println("📩 " + notification);
    }

    // Helper: Check if a notification was sent (for testing)
    public boolean hasNotificationForCustomer(CustomerProfile customer, String message) {
        String expected = String.format("Notification to %s: %s", customer.getUserName(), message);
        return notificationLog.contains(expected);
    }

    // Existing methods (unchanged or partially shown for context)
    public void addMealToOrderHistory(CustomerProfile customer, String mealName) {
        meal matchedMeal = meals.stream()
                .filter(m -> m.getName().equalsIgnoreCase(mealName))
                .findFirst()
                .orElse(null);

        if (matchedMeal == null) {
            System.out.println("⚠️ Meal not found: " + mealName);
            return;
        }

        orderHistory.putIfAbsent(customer, new ArrayList<>());
        orderHistory.get(customer).add(new order(customer, matchedMeal));
        System.out.printf("✅ Order added to %s's history: %s\n", customer.getUserName(), mealName);
    }

    public List<meal> getFilteredSuggestedMeals(CustomerProfile profile) {
        return meals.stream()
                .filter(m -> !m.containsAllergen(profile.getAllergy()))
                .filter(m -> m.getDietaryCategory().equalsIgnoreCase(profile.getDietaryPreference()))
                .collect(Collectors.toList());
    }

    public void addToPendingOrders(CustomerProfile customer, meal meal) {
        pendingOrders.add(new order(customer, meal));
        System.out.println("⚠️ Order added to pending list. Please confirm it before submission.");
    }

    public List<order> getPendingOrdersForCustomer(CustomerProfile customer) {
        return pendingOrders.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }
    public List<meal> getMeals() {
        return new ArrayList<>(meals); // Return a copy to prevent external modification
    }

}