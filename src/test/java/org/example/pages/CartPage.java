package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    public void proceedToCheckout() {
        click(checkoutButton);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }
}

