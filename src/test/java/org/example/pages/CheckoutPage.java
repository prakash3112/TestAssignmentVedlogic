package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name='postalCode']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//input[@name='continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;

    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private WebElement completeHeader;

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void finishCheckout() {
        click(finishButton);
    }

    public boolean isCheckoutSuccessful() {
        return getText(completeHeader).equals("Thank you for your order!");
    }
}
