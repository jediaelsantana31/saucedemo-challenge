package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Checkout extends SeleniumHelper {

    public void validateCartItems(List<String> expectedProductNames) {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));

        for (String expectedProductName : expectedProductNames) {
            WebElement cartItem = findCartItemByName(cartItems, expectedProductName);
            validateCartItem(cartItem, expectedProductName);
        }
    }

    public void validatePaymentAndShippingInfo(String expectedPaymentInfo, String expectedShippingInfo) {
        String actualPaymentInfo = findElement(By.cssSelector(".summary_value_label")).getText();
        String actualShippingInfo = findElement(By.cssSelector(".summary_info_label")).getText();

        Assert.assertEquals("Payment information mismatch", expectedPaymentInfo, actualPaymentInfo);
        Assert.assertEquals("Shipping information mismatch", expectedShippingInfo, actualShippingInfo);
    }

    public void validateTotalPrice(String expectedTotalPrice) {
        String actualTotalPrice = findElement(By.cssSelector(".summary_total_label")).getText();
        Assert.assertEquals("Total price mismatch", expectedTotalPrice, actualTotalPrice);
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

    private void validateCartItem(WebElement cartItem, String expectedProductName) {
        if (cartItem == null) {
            Assert.fail("Product not found in the cart: " + expectedProductName);
        }

        String itemName = cartItem.findElement(By.cssSelector(".inventory_item_name")).getText();
        Assert.assertEquals("Product name mismatch for: " + expectedProductName, expectedProductName, itemName);
    }
}
