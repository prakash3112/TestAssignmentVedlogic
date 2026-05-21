package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@class='cart_item']")
    private List<WebElement> cartItems;

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }
}

