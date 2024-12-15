@d
Feature: Negative Scenarios

  Background:
    Given User is on landing page "https://www.demoblaze.com/"

  @InvalidSignUpCredentials
  Scenario Outline: Try signing up with an existing username and verify the error message.

    When I click on the Sign Up button
    When I enter a unique username as "<userName>" and password as "<passWord>"
    And I confirm the Sign Up
    Then User should be able to see this user already exists message.

    Examples:
      | userName   | passWord |
      | Project007 | admin123 |

  @InvalidLogInCredentials
  Scenario Outline: Try logging in with new username and verify the error message.

    When I click on the Log In button
    When I enter a valid username as "<userName>" and password as "<passWord>"
    And  I confirm the Log In
    Then I should be able to see Wrong password.

    Examples:
      | userName   | passWord    |
      | Project007 | password123 |

  @ExpiredCard
  Scenario Outline: Purchase with expired card

    Given I am logged in as "Project201" with password as "strongpassword"
    When I click on laptops
    When I add Sony vaio i5 to the cart from Laptops
    Then Product should be present in the cart
    When I view the cart
    Then the total amount should be calculated correctly.
    Given I have products in the cart
    When I click on Place Order
    And I fill in the purchase details as "<user>", "<country>", "<city>", "<cardno>", "<month>", "<year>"
    When I confirm the purchase
    Then Expired Card message should appear

    Examples:
      | user      | country | city   | cardno         | month | year |
      | Test User | KSA     | Jeddah | 12345678901234 | 12    | 2000 |

  Scenario: Add product twice and verify quantity
    Given I am logged in as "Project203" with password as "strongpassword"
    When I click on laptops
    And I add Sony vaio i5 to the cart from Laptops
    Then Product should be present in the cart
    And I press again on add to cart
    Then Product should be present in the cart
    When I view the cart
    Then the quantity of "Sony vaio i5"
