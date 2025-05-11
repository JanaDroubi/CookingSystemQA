Feature: Special Cook Project Management System
As a customer, chef, or kitchen manager
I want an efficient system to handle meal planning, ingredient sourcing, scheduling, and customer preferences
So that cooking projects can be managed seamlessly

  Scenario: Customer stores dietary preferences and allergies
    Given a new customer "John Doe" creates a profile
    When he enters:
      | Preference Type | Value          |
      | Diet            | Pescatarian    |
      | Allergies       | Shellfish      |
    And saves the profile
    Then the system shows a confirmation: "Profile saved for John Doe (Pescatarian, Shellfish-Free)"
    And future meal recommendations exclude:
      | Beef           |
      | Shellfish      |

  Scenario: Chef views customer dietary restrictions
    Given customer "Alice" has these restrictions:
      | Dietary Need | Details          |
      | Allergy      | Peanuts          |
      | Preference   | Halal            |
    When chef "Ahmed Khan" opens "Alice"'s profile
    Then the system displays:
      """
      DIETARY FLAGS:
      ⚠️ Peanuts Allergy
      ✔️ Halal Certified
      """
    And any recipe containing "peanuts" is marked "Unsafe"

  Scenario: Customer reorders from past meals
    Given customer "Alice" has order history:
      | Date       | Meal               | Price |
      | 2023-11-10 | classicToast       | $14   |
      | 2023-11-12 |sweetBites          | $18   |
    When he selects "Re-order" for "sweetBites"
    Then the system pre-fills his cart with:
      | Item        | Customization       |
      | Mapo Tofu   | Spiciness: Medium   |
    And shows: "Last ordered Nov 10 - Ready in 45 mins"

  Scenario: System blocks invalid ingredient combinations
    Given customer "Emma Clark" is "Gluten-Free"
    When she tries to order "Classic Wheat Pizza"
    Then the system shows:
      """
      ❌ Cannot Order:
      - Contains gluten (wheat flour)
      Suggested Alternatives:
      1. Cauliflower Crust Pizza (+$3)
      2. Gluten-Free Flour Pizza
      """
    And prevents checkout until resolved



  Scenario: Kitchen manager handles low inventory
    Given current stock levels:
      | Ingredient       | Quantity | Threshold |
      | Organic Tomatoes | 4 lbs    | 10 lbs    |
      | Basil            | 0.5 lbs  | 2 lbs     |
    When the inventory report runs
    Then the kitchen manager sees:
      """
      🔴 CRITICAL:
      - Basil: 0.5 lbs (order 5 lbs)
      🟡 WARNING:
      - Organic Tomatoes: 4 lbs (order 6 lbs)
      """
    And the "Order Now" button is enabled