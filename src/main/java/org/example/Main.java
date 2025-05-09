package org.example;

import all.*;

import java.util.List;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyApplication app = new MyApplication();

        while (true) { // Infinite loop
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║        Welcome to the Special Cook Project Management System          ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

            // Login Process
            System.out.println("╔═══════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                               Login page                              ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

            System.out.print("👤 Enter username: ");
            String username = scanner.nextLine();
            System.out.print("🔑 Enter password: ");
            String password = scanner.nextLine();

            app.setUsernameAndPassAndPassFromSystem(username, password);

            if (!app.getValidation()) {
                System.out.println("❌ Invalid username or password.");
                continue; // Restart the loop
            }

            String role = app.getLoggedInUserRole();

            if (role.equalsIgnoreCase("chef")) {
                chefMenu(app, scanner, username);
            } else if (role.equalsIgnoreCase("managerj")) {
                kitchenManagerMenu(app, scanner);
            } else {
                System.out.println("❌ Unknown role. Access denied.");
            }
        }
    }



   private static void chefMenu(MyApplication app, Scanner scanner, String username) {
        System.out.println("\n👨‍🍳 Chef Menu 👨‍🍳");
        while (true) {
            System.out.println("1️⃣ View Assigned Tasks");
            System.out.println("2️⃣ Update Task Status");
            System.out.println("3️⃣ Logout");
            System.out.println("4️⃣ View Custom Meal Requests");
            System.out.println("5️⃣ View Ingredient Availability");
            System.out.println("6️⃣ Suggest Ingredient Substitutions");
            System.out.println("7️⃣ View Customer Preferences");
            System.out.println("8️⃣ View Past Orders");
            System.out.println("9️⃣ View Meal Plan Suggestions");

            System.out.print("👨‍🍳 Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> app.viewChefTasks(username);
                case 2 -> {
                    System.out.print("✏️ Enter task number to mark as complete: ");
                    int taskIndex = scanner.nextInt();
                    scanner.nextLine();
                    app.completeChefTask(username, taskIndex);
                }
                case 3 -> {
                    System.out.println("👋 Logging out...");
                    return;
                }
            //    case 4 -> app.viewCustomMealRequests(username);
            //    case 5 -> app.viewIngredientAvailability(username);
            //    case 6 -> app.suggestIngredientSubstitutions(username);
            //    case 7 -> app.viewCustomerPreferences(username);
            //    case 8 -> app.viewPastOrders(username);
            //    case 9 -> app.viewMealPlanSuggestions(username);
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    private static void kitchenManagerMenu(MyApplication app, Scanner scanner) {
        System.out.println("\n🍽️ Kitchen Manager Menu 🍽️");
        while (true) {
            System.out.println("1️⃣ View Inventory");
            System.out.println("2️⃣ Add Ingredient");
            System.out.println("3️⃣ Use Ingredient");
            System.out.println("4️⃣ Restock Ingredient");
            System.out.println("5️⃣ Assign Task to Chef");
            System.out.println("6️⃣ View Chef Tasks");
            System.out.println("7️⃣ Logout");

            System.out.print("🍽️ Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            //    case 1 -> app.showInventory();
                case 2 -> {
                    System.out.print("📝 Ingredient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("📦 Quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("⚠️ Threshold: ");
                    int threshold = scanner.nextInt();
                    System.out.print("📝 Ingredient alternative: ");
                    String alternative1 = scanner.nextLine();
                    scanner.nextLine();
                    app.ingredients.add(new Ingredient(name,qty,threshold,app.findalternative(alternative1)));

                }
                case 3 -> {
                    System.out.print("📝 Ingredient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("📉 Quantity to use: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    app.useIngredient(name, qty);
                }
                case 4 -> {
                    System.out.print("📝 Ingredient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("📈 Quantity to restock: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    app.restockIngredient(name, qty);
                }
                case 5 -> {
                    // Fetch and display the available chefs
                    List<chef> availableChefs = app.getChefs(); // Assuming this returns a list of chef objects
                    if (availableChefs.isEmpty()) {
                        System.out.println("❌ No available chefs.");
                        break;
                    }

                    // Display the available chefs by name
                    System.out.print("👨‍🍳 Available Chefs: ");
                    availableChefs.forEach(chef -> System.out.print(chef.getUserName() + " ")); // Assuming the chef class has a getName() method
                    System.out.println();

                    String chefName = "";
                    boolean validChef = false;

                    // Get chef name and validate input
                    while (!validChef) {
                        System.out.print("👨‍🍳 Enter Chef Name: ");
                        chefName = scanner.nextLine();

                        // Check if the entered chef name exists in the list
                        String finalChefName1 = chefName;
                        validChef = availableChefs.stream()
                                .anyMatch(chef -> chef.getUserName().equalsIgnoreCase(finalChefName1));

                        if (!validChef) {
                            System.out.println("❌ Chef not found. Please try again.");
                        }
                    }

                    // Get task description
                    System.out.print("📝 Task Description: ");
                    String task = scanner.nextLine();

                    // Find the selected chef by name
                    String finalChefName = chefName;
                    chef selectedChef = availableChefs.stream()
                            .filter(chef -> chef.getUserName().equalsIgnoreCase(finalChefName))
                            .findFirst()
                            .orElse(null);

                    // Assign task to the selected chef
                    if (selectedChef != null) {
                        app.assignTaskToChef(String.valueOf(selectedChef), task);
                        System.out.println("✅ Task successfully assigned to " + selectedChef.getUserName());
                    } else {
                        System.out.println("❌ Error: Chef not found during task assignment.");
                    }
                }

                case 6 -> {
                    // Assuming you want to print tasks for all chefs
                    List<chef> allChefs = app.getChefs(); // Get the list of all chefs (assuming app.getChefs() returns a list of chefs)

                    if (allChefs.isEmpty()) {
                        System.out.println("❌ No chefs available.");
                    } else {
                        for (int i = 0; i < allChefs.size(); i++) {
                            chef ch = allChefs.get(i);
                            System.out.println("👨‍🍳 Tasks for Chef: " + ch.getUserName());
                            app.viewChefTasks(String.valueOf(i)); // Call viewChefTasks with the chef index (or unique identifier)
                            System.out.println(); // Add a blank line for better readability between chefs
                        }
                    }
                }

                case 7 -> {
                    System.out.println("👋 Logging out...");
                    return;
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}