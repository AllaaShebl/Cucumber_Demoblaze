
@a

Feature: User Sign Up

  Background:
    Given User is on landing page "https://www.demoblaze.com/"

  @ValidCredentials
  Scenario: User Signup

    When I click on the Sign Up button
    And I enter a unique username as "Project202" and password as "strongpassword"
    And I confirm the Sign Up
    Then I should see a success message Sign up successful.
