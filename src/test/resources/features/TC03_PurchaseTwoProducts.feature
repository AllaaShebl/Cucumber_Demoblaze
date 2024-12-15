@c
Feature: Purchase products


  Scenario: Purchase Two Laptops
    Given User is on landing page "https://www.demoblaze.com/"
    And I am logged in as "Project202" with password as "strongpassword"
    When I click on laptops
    When I add Sony vaio i5 to the cart from Laptops
    Then Product one should be present in the cart
    When I press home
    And I click on laptops
    And I add Dell i7 8gb to the cart from Laptops
    Then Product should be present in the cart
    When I view the cart
    Then the total amount should be calculated correctly.
    Given I have products in the cart
    When I click on Place Order
    And I fill in the purchase details as "Test User", "Test Country", "Test City", "1234567812345678", "12", "2026"
    And I confirm the purchase
    Then I should see a success message "Thank you for your purchase!"