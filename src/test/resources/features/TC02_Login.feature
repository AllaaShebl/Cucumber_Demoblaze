@b
Feature: User login

  Background:
    Given User is on landing page "https://www.demoblaze.com/"

Scenario: User Login

When I click on the Log In button
And I enter a valid username as "Project202" and password as "strongpassword"
And I confirm the Log In
Then I should see the user account page.

