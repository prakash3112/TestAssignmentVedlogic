package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_item:first-child .btn_inventory")
    private WebElement firstItemAddToCartButton;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public void addFirstItemToCart() {
        click(firstItemAddToCartButton);
    }

    public void goToCart() {
        click(cartLink);
    }
}
