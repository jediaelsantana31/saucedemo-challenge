package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class ShoppingCart extends SeleniumHelper {

    public ShoppingCart cartIsDisplayed() {
        iSee("Your Cart");
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

    public void clickCheckoutButton() {
        clickElement(By.id("checkout"));

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
        log("Quantity grabbed from " + cartItem + ": " + cartItem.getText());
        return Integer.parseInt(quantityText.trim());
    }

    private void clickRemoveButton(WebElement cartItem) {
        log("Click to remove the product: " + cartItem.getText());
        cartItem.findElement(By.xpath(".//button[contains(text(), 'Remove')]")).click();
    }

    private WebElement findCartItemByName(List<WebElement> cartItems, String productName) {
        for (WebElement cartItem : cartItems) {
            String productNameInCart = null;
            try {
                productNameInCart = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText();
                if (productName.equals(productNameInCart)) {
                    log("Found cart item with product name: " + productName);
                    return cartItem;
                }
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                log("Error while retrieving product name from cart item: " + e.getMessage());
            }
        }
        log("Cart item not found for product name: " + productName);
        return null;
    }

}
