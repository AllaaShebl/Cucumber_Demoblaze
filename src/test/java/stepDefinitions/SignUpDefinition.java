package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignUpDefinition {

    Hooks hooks;


    @Given("User is on landing page {string}")
    public void openurl(String url) {

        hooks.driver.get(url);

    }
    @When("I click on the Sign Up button")
    public void clicksignup() {
        hooks.driver.findElement(By.id("signin2")).click();
    }

    @When("I enter a unique username as {string} and password as {string}")
    public void goToSignupPage(String userName, String passWord) {

        hooks.driver.findElement(By.id("sign-username")).sendKeys(userName);
        hooks.driver.findElement(By.id("sign-password")).sendKeys(passWord);
    }

    @When("I confirm the Sign Up")
    public void clicksignupbtn() {
        hooks.driver.findElement(By.xpath("//button[text()='Sign up']")).click();

    }

    @Then("I should see a success message Sign up successful.")
    public void verifySignup() {

        try { WebDriverWait wait = new WebDriverWait(hooks.driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = hooks.driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "Sign up successful.");
        System.out.println("Alert text: " + alertMessage);
        hooks.driver.switchTo().alert().accept();}
        catch (AssertionError e) {
            System.out.println("Assertion failed: The alert message does not match the expected value.");
            System.out.println("Expected: 'Sign up successful.' but found: " + hooks.driver.switchTo().alert().getText());
            throw e;
        } catch (Exception e) {
            System.out.println("An error occurred while verifying the alert message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("User should be able to see this user already exists message.")
    public void verifyUserExist() {

       try { WebDriverWait wait = new WebDriverWait(hooks.driver, Duration.ofSeconds(10));
              wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = hooks.driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "This user already exist.");
        System.out.println("Negative Scenario Alert text: " + alertMessage);
        hooks.driver.switchTo().alert().accept();}
       catch (AssertionError e) {
           System.out.println("Assertion failed: The alert message does not match the expected value.");
           System.out.println("Expected: 'This user already exist.' but found: " + hooks.driver.switchTo().alert().getText());
           throw e;
       } catch (Exception e) {
           System.out.println("An error occurred while verifying the alert message: " + e.getMessage());
           e.printStackTrace();
       }

    }
}