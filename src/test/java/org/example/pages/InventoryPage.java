package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_item:first-child .btn_inventory")
    private WebElement firstItemAddToCartButton;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    public void addFirstItemToCart() {
        click(firstItemAddToCartButton);
    }

    public void goToCart() {
        click(cartLink);
    }

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }
}

