package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartLink;

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> inventoryItems;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    private List<WebElement> inventoryItemNames;

    public void addFirstItemToCart() {
        if (inventoryItems != null && !inventoryItems.isEmpty()) {
            WebElement first = inventoryItems.get(0).findElement(org.openqa.selenium.By.cssSelector(".btn_inventory"));
            click(first);
        }
    }

    public void addItemToCartByName(String productName) {
        for (int i = 0; i < inventoryItemNames.size(); i++) {
            String name = inventoryItemNames.get(i).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                WebElement item = inventoryItems.get(i);
                WebElement addButton = item.findElement(org.openqa.selenium.By.cssSelector(".btn_inventory"));
                click(addButton);
                return;
            }
        }
        throw new IllegalArgumentException("Product not found: " + productName);
    }

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        click(cartLink);
    }

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }
}

