Feature: Ingredient Management
  As a kitchen manager
  I want to manage ingredients and their alternatives
  So that I can maintain proper stock levels and substitutions

  Scenario: Check ingredient stock level
    Given I have an ingredient "Tomato" with quantity 10 and threshold 5
    When I check if "Tomato" is low stock
    Then it should not be low stock

  Scenario: Detect low stock ingredient
    Given I have an ingredient "Onion" with quantity 3 and threshold 5
    When I check if "Onion" is low stock
    Then it should be low stock

  Scenario: Reduce ingredient quantity
    Given I have an ingredient "Garlic" with quantity 15 and threshold 10
    When I reduce "Garlic" quantity by 6
    Then the remaining quantity should be 9
    And I should see a restock alert for "Garlic"

  Scenario: Restock ingredient
    Given I have an ingredient "Cheese" with quantity 2 and threshold 5
    When I restock "Cheese" with 10
    Then the new quantity should be 12

  Scenario: Set ingredient alternative
    Given I have an ingredient "Basil" with quantity 5 and threshold 3
    And I have an ingredient "Oregano" with quantity 10 and threshold 5
    When I set "Oregano" as alternative for "Basil"
    Then the alternative for "Basil" should be "Oregano"