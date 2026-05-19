package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.*;
import static org.junit.Assert.*;

public class UiSteps {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    // Lazy initialization helpers - pages will be created after the driver
    // is initialized in Hooks (DriverFactory). This avoids creating the
    // WebDriver from page constructors before hooks run.
    private LoginPage loginPage() {
        if (loginPage == null) {
            if (org.example.utils.DriverFactory.getCurrentDriver() == null) {
                throw new IllegalStateException("WebDriver is not initialized. Ensure Hooks has initialized the driver before using pages.");
            }
            loginPage = new LoginPage(org.example.utils.DriverFactory.getCurrentDriver());
        }
        return loginPage;
    }

    private InventoryPage inventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(org.example.utils.DriverFactory.getCurrentDriver());
        }
        return inventoryPage;
    }

    private CartPage cartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(org.example.utils.DriverFactory.getCurrentDriver());
        }
        return cartPage;
    }

    private CheckoutPage checkoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(org.example.utils.DriverFactory.getCurrentDriver());
        }
        return checkoutPage;
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        loginPage().navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage().login(username, password);
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertTrue(loginPage().isLoginSuccessful());
    }

    @Given("I am logged in to SauceDemo")
    public void iAmLoggedInToSauceDemo() {
        loginPage().navigateToLoginPage();
        loginPage().login("standard_user", "secret_sauce");
        assertTrue(loginPage().isLoginSuccessful());
    }

    @When("I add the first item to the cart")
    public void iAddTheFirstItemToTheCart() {
        inventoryPage().addFirstItemToCart();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        inventoryPage().goToCart();
        cartPage().proceedToCheckout();
    }

    @When("I fill in checkout information with {string} {string} {string}")
    public void iFillInCheckoutInformationWith(String firstName, String lastName, String postalCode) {
        checkoutPage().fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @Then("the checkout should be successful")
    public void theCheckoutShouldBeSuccessful() {
        checkoutPage().finishCheckout();
        assertTrue(checkoutPage().isCheckoutSuccessful());
    }

    @Then("the cart should contain {int} item(s)")
    public void theCartShouldContainItems(int itemCount) {
        assertEquals(itemCount, cartPage().getCartItemCount());
    }

    @Then("the inventory page should display items")
    public void theInventoryPageShouldDisplayItems() {
        int itemCount = inventoryPage().getInventoryItemCount();
        assertTrue("Inventory page should display items", itemCount > 0);
        System.out.println("Inventory page displays " + itemCount + " items");
    }
}
