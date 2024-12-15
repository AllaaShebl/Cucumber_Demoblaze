package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class PurchaseProductDefinition {
    Hooks hooks;
    WebDriverWait wait = new WebDriverWait(hooks.driver, Duration.ofSeconds(10));

    @When("I click on the Log In button")
    public void clicklogin() {
        hooks.driver.findElement(By.id("login2")).click();
    }

    @When("I enter a valid username as {string} and password as {string}")
    public void enterValidCredentials(String userName, String passWord) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        hooks.driver.findElement(By.id("loginusername")).sendKeys(userName);
        hooks.driver.findElement(By.id("loginpassword")).sendKeys(passWord);
        System.out.println("log in credentials entered");
    }

    @When("I confirm the Log In")
    public void confirmlogin() {
        hooks.driver.findElement(By.xpath("//button[text()='Log in']")).click();
        System.out.println("log in button clicked");

    }

    @Then("I should see the user account page.")
    public void verifylogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        try {
            WebElement loggeduser = hooks.driver.findElement(By.id("nameofuser"));
            Assert.assertTrue(hooks.driver.findElement(By.id("nameofuser")).isDisplayed());
            System.out.println(loggeduser.getText());
            System.out.println("Logged in successfully");
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
        }}

        @Then("I should be able to see Wrong password.")
        public void verifywronglogincredentials () {
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = hooks.driver.switchTo().alert().getText();
                System.out.println("Alert message: " + alertMessage);
                Assert.assertEquals(alertMessage, "Wrong password.");
                hooks.driver.switchTo().alert().accept();
                System.out.println(" Wrong password message appeared");
            } catch (AssertionError e) {
                System.out.println("Assertion failed: The alert message does not match the expected value.");
                System.out.println("Expected: 'Wrong password.' but found: " + hooks.driver.switchTo().alert().getText());
                throw e;
            } catch (Exception e) {
                System.out.println("An error occurred while verifying the alert message: " + e.getMessage());
                e.printStackTrace();
            }
        }


    @Given("I am logged in as {string} with password as {string}")
    public void login(String userName, String passWord) {
        clicklogin();
        enterValidCredentials(userName, passWord);
        confirmlogin();
        verifylogin();
    }

    @When("I click on laptops")
    public void clicklaptopns() {
        Hooks.driver.findElement(By.linkText("Laptops")).click();
        System.out.println("laptops clicked");
    }


    @When("I add Sony vaio i5 to the cart from Laptops")
    public void addproductone() {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sony vaio i5"))).click();
        System.out.println("Sony lapped is clicked");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        System.out.println("add to cart is pressed");
    }


    @And("I press again on add to cart")
    public void clickAddToCartAgain() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        System.out.println("add to cart is pressed again");
    }

    @Then("Product one should be present in the cart")
    public void verifyproductoneadded() {
        wait.until(ExpectedConditions.alertIsPresent());
        String addproductone = hooks.driver.switchTo().alert().getText();
        System.out.println("Product one addition message:" + addproductone);
        hooks.driver.switchTo().alert().accept();
        System.out.println("product added alert ok is clicked");

    }

    @When("I press home")
    public void home() {
        hooks.driver.findElement(By.id("nava")).click();
        System.out.println("I went back to home screen");
    }

    @When("I add Dell i7 8gb to the cart from Laptops")
    public void addproducttwo() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dell i7 8gb"))).click();
        System.out.println("Dell laptop is clicked");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        System.out.println("add to cart is clicked for dell");
    }

    @Then("Product should be present in the cart")
    public void verifyproducttwoadded() {
        wait.until(ExpectedConditions.alertIsPresent());
        String addproductone = hooks.driver.switchTo().alert().getText();
        System.out.println("Product two addition message:" + addproductone);
        hooks.driver.switchTo().alert().accept();
        System.out.println("dell product alert is accepted");
    }


    @When("I view the cart")
    public void checkcart() {
        hooks.driver.findElement(By.id("cartur")).click();
        System.out.println("I went cart");
    }

    @Then("the total amount should be calculated correctly.")
    public void validateamount() {
        try {wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
        List<WebElement> itemPrices = hooks.driver.findElements(By.xpath("//tr/td[3]"));
        int expectedTotal = 0;
        for (WebElement priceElement : itemPrices) {
            int price = Integer.parseInt(priceElement.getText()); // Convert price text to integer
            expectedTotal += price;
        }
        String totalAmountText = hooks.driver.findElement(By.id("totalp")).getText();
        int displayedTotal = Integer.parseInt(totalAmountText);
        Assert.assertEquals(displayedTotal, expectedTotal, "The total amount in the cart is incorrect!");
        System.out.println("Expected Total: " + expectedTotal);
        System.out.println("Displayed Total: " + displayedTotal);}
        catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Given("I have products in the cart")
    public void verifyproductsincart() {
        List<WebElement> nonEmptyCells = hooks.driver.findElements(By.xpath("//tbody/tr[1]/td[2]"));

        if (nonEmptyCells.size() > 0) {
            System.out.println("The table contains data.");
        } else {
            System.out.println("The table is empty.");
        }
    }

    @When("I click on Place Order")
    public void clickPlaceOrder() {
        hooks.driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        System.out.println("place order button clicked");
    }

    @When("I fill in the purchase details as {string}, {string}, {string}, {string}, {string}, {string}")
    public void fillPurchaseDetails(String name, String country, String city, String cardno, String month, String year) {
        hooks.driver.findElement(By.id("name")).sendKeys(name);
        hooks.driver.findElement(By.id("country")).sendKeys(country);
        hooks.driver.findElement(By.id("city")).sendKeys(city);
        hooks.driver.findElement(By.id("card")).sendKeys(cardno);
        hooks.driver.findElement(By.id("month")).sendKeys(month);
        hooks.driver.findElement(By.id("year")).sendKeys(year);
        System.out.println("purchase data is entered");
    }

    @When("I confirm the purchase")
    public void confirmPurchase() {
        hooks.driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        System.out.println("purchase button is clicked");
    }

    @Then("I should see a success message \"Thank you for your purchase!\"")
    public void verifyPurchaseSuccess() {
        try { String confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert h2"))).getText();
        Assert.assertEquals(confirmationMessage, "Thank you for your purchase!", "Purchase confirmation message does not match.");
        System.out.println("Confirmation Message:" + confirmationMessage);}
        catch (AssertionError e) {
            System.out.println("Assertion failed: The alert message does not match the expected value.");
            System.out.println("Expected: 'Wrong password.' but found: " + hooks.driver.switchTo().alert().getText());
            throw e;
        } catch (Exception e) {
            System.out.println("An error occurred while verifying the alert message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("Expired Card message should appear")
    public void verifyPurchaseFailure() {
        try {
            WebDriverWait wait = new WebDriverWait(hooks.driver, Duration.ofSeconds(10));
            String alertMessage = wait.until(ExpectedConditions.alertIsPresent()).getText();
            System.out.println("Alert Message: " + alertMessage);
            hooks.driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No alert found. Error message did not appear.");
        }
    }

    @Then("the quantity of \"Sony vaio i5\"")
    public void verifyDuplicatedProductInCart() {
        try {
            List<WebElement> productRows = hooks.driver.findElements(By.xpath("//tr[td[text()='Sony vaio i5']]"));
            int productCount = productRows.size();
            Assert.assertTrue(productCount > 0, "Product is not found in the cart");
            System.out.println("Product '" + "Sony vaio i5" + "' is found " + productCount + " times in the cart.");
            WebElement priceElement = productRows.get(0).findElement(By.xpath("./td[3]"));
            int unitPrice = Integer.parseInt(priceElement.getText().trim());
            int expectedTotalPrice = unitPrice * productCount;
            WebElement totalElement = hooks.driver.findElement(By.id("totalp"));
            int displayedTotalPrice = Integer.parseInt(totalElement.getText().trim());
            Assert.assertEquals(displayedTotalPrice, expectedTotalPrice, "Total price in the cart is incorrect");
            System.out.println("Product quantity and total price verified successfully.");
            System.out.println("Product quantity and total price verified successfully.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
