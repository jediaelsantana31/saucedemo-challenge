package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCart extends SeleniumHelper {
    public ShoppingCart cartIsDisplayed() {
         iSeeTitle("Your Cart", 3);
        return this;
    }

    public void validateCartItems(List<String> productNames) {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));
        int totalQuantity = 0;

        for (String productName : productNames) {
            WebElement cartItem = findCartItemByName(cartItems, productName);
            processCartItem(cartItem, productName);
            totalQuantity += getCartItemQuantity(cartItem);
        }

        Assert.assertEquals("Number of products different than expected", productNames.size(), totalQuantity);
    }

    public void removeItemsFromCart(List<String> productNames) {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));

        for (String productName : productNames) {
            WebElement cartItem = findCartItemByName(cartItems, productName);
            processCartItem(cartItem, productName);
            clickRemoveButton(cartItem);
        }
    }

    public void validateProductNotInCart(List<String> productNames) {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));

        for (String productName : productNames) {
            WebElement cartItem = findCartItemByName(cartItems, productName);
            if (cartItem != null) {
                throw new AssertionError("Product found in the cart, but it shouldn't be: " + productName);
            }
        }
    }

    private void processCartItem(WebElement cartItem, String productName) {
        if (cartItem == null) {
            throw new AssertionError("Product not found in the cart: " + productName);
        }

        String itemName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText();
        Assert.assertEquals("Product name mismatch for: " + productName, productName, itemName);
    }

    private int getCartItemQuantity(WebElement cartItem) {
        String quantityText = cartItem.findElement(By.cssSelector(".cart_quantity")).getText();
        return Integer.parseInt(quantityText.trim());
    }

    private WebElement findCartItemByName(List<WebElement> cartItems, String productName) {
        for (WebElement cartItem : cartItems) {
            String productNameInCart = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (productName.equals(productNameInCart)) {
                return cartItem;
            }
        }
        return null;
    }

    public void clickCheckoutButton() {
        clickElement(By.id("checkout"));

    }

    private void clickRemoveButton(WebElement cartItem) {
        cartItem.findElement(By.xpath(".//button[contains(text(), 'Remove')]")).click();
    }

}
