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

    public UiSteps() {
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        assertTrue(loginPage.isLoginSuccessful());
    }

    @Given("I am logged in to SauceDemo")
    public void iAmLoggedInToSauceDemo() {
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(loginPage.isLoginSuccessful());
    }

    @When("I add the first item to the cart")
    public void iAddTheFirstItemToTheCart() {
        inventoryPage.addFirstItemToCart();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
    }

    @When("I fill in checkout information with {string} {string} {string}")
    public void iFillInCheckoutInformationWith(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutInfo(firstName, lastName, postalCode);
    }

    @Then("the checkout should be successful")
    public void theCheckoutShouldBeSuccessful() {
        checkoutPage.finishCheckout();
        assertTrue(checkoutPage.isCheckoutSuccessful());
    }
}
