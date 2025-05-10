Feature: Chef Task Management

  Scenario Outline: View assigned cooking tasks
    Given a chef is logged into the system
    And the task details:
      | Chef Name  | Assigned Tasks          | Notification Received |
      | <ChefName> | <Task>                  | <Notification>        |
    When they check their task list
    Then the system should display all assigned tasks
    And notify the chef of upcoming cooking deadlines

    Examples:
      | ChefName   | Task                   | Notification |
      | Chef John  | Prepare Vegan Salad     | Yes          |
      | Chef Lisa  | Bake Gluten-Free Cake   | Yes          |

  Scenario Outline: Approve or adjust ingredient substitutions
    Given a customer has selected an alternative ingredient
    And the substitution details:
      | Customer Name | Original Ingredient | Substituted Ingredient | Chef Approval |
      | <Customer>    | <Original>          | <Substituted>          | <Approval>    |
    When the system notifies the chef
    Then the chef should approve or adjust the recipe

    Examples:
      | Customer | Original    | Substituted | Approval  |
      | Alice    | Almond Milk | Soy Milk    | Approved  |
      | Bob      | Sugar       | Honey       | Adjusted  |


  Scenario Outline: View customer dietary preferences
    Given a chef wants to customize a meal
    And the dietary details:
      | Customer Name | Dietary Preference | Allergy |
      | <Customer>    | <Preference>       | <Allergy> |
    When they access a customer's profile
    Then the system should display the customer's dietary preferences and allergies

    Examples:
      | Customer | Preference  | Allergy  |
      | Alice    | Vegetarian  | Nuts     |
      | Mark     | Vegan       | Dairy    |

  Scenario Outline: Access customers' order history
    Given a chef wants to suggest a meal plan
    And the order history details:
      | Customer Name | Last Ordered Meal       |
      | <Customer>    | <LastMeal>             |
    When they access a customer's order history
    Then the system should display past orders

    Examples:
      | Customer | LastMeal               |
      | Sarah    | Grilled Chicken Salad  |
      | Tom      | Gluten-Free Pasta      |
#
#Feature: Notifications and Alerts
#
#  Scenario Outline: Notify chefs of scheduled cooking tasks
#    Given a chef has upcoming meal preparations
#    And the notification details:
#      | Chef Name  | Task               | Notification Sent |
#      | <ChefName> | <Task>             | <Notification>    |
#    When their schedule updates
#    Then the system should send them a notification
#
#    Examples:
#      | ChefName  | Task              | Notification |
#      | Chef Mike | Prepare Sushi     | Yes          |
#      | Chef Emma | Bake Birthday Cake| Yes          |

#  Scenario Outline: Receive an alert for ingredient substitution
#    Given a chef is preparing a meal
#    And the alert details:
#      | Chef Name | Meal           | Substituted Ingredient | Alert Received |
#      | <ChefName>| <Meal>         | <Substitution>         | <Alert>        |
#    When an ingredient substitution is applied
#    Then the system should send an alert to the chef for approval or adjustment
#
#    Examples:
#      | ChefName | Meal          | Substitution           | Alert |
#      | Chef Alan| Vegan Burger  | Tofu for Tempeh        | Yes   |
#      | Chef Nina| Pasta Alfredo | Cashew Cream for Dairy | Yes   |