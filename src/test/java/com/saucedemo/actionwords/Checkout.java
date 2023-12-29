package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.base.UserData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Checkout extends SeleniumHelper {

    public Checkout overviewIsDisplayed() {
        iSee("Checkout: Overview");
        return this;
    }

    public Checkout proceedToCheckout(UserData userData) {
        iSee("Checkout: Your Information");
        fillUserData(userData);
        clickElement(By.id("continue"));
        return this;
    }

    public Checkout validateCartItems(List<String> expectedProductNames) {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));

        for (String expectedProductName : expectedProductNames) {
            WebElement cartItem = findCartItemByName(cartItems, expectedProductName);
            validateCartItem(cartItem, expectedProductName);
        }

        return this;
    }

    public Checkout validatePaymentAndShippingInfo(String expectedPaymentInfo, String expectedShippingInfo) {
        String actualPaymentInfo = getSummaryLabelValue("Payment Information");
        Assert.assertEquals("Payment information mismatch", expectedPaymentInfo, actualPaymentInfo);

        String actualShippingInfo = getSummaryLabelValue("Shipping Information");
        Assert.assertEquals("Shipping information mismatch", expectedShippingInfo, actualShippingInfo);

        return this;
    }

    public Checkout validateTotalPrice() {
        BigDecimal totalPrice = calculateTotalPrice();
        BigDecimal actualItemTotal = getPriceTotal(".summary_subtotal_label");

        Assert.assertEquals("Item total mismatch", 0, totalPrice.compareTo(actualItemTotal));

        boolean isTaxDisplayed = isElementDisplayed(By.cssSelector(".summary_tax_label"));
        Assert.assertTrue("Tax should be displayed", isTaxDisplayed);

        BigDecimal tax = getPriceTotal(".summary_tax_label");
        BigDecimal total = totalPrice.add(tax);
        BigDecimal actualTotalPrice = getPriceTotal("div.summary_total_label");

        Assert.assertEquals("Total price mismatch", 0, total.compareTo(actualTotalPrice));

        return this;
    }

    public Checkout validateSuccessMessage(String expectedMessage) {
        clickElement(By.id("finish"));
        iSee("Checkout: Complete!");
        String actualSuccessMessage = grabTextFrom(By.cssSelector("h2.complete-header"));
        Assert.assertEquals("Error completing the order - " + expectedMessage, expectedMessage, actualSuccessMessage);
        return this;
    }

    private void fillUserData(UserData userData) {
        fillField(By.id("first-name"), userData.getFirstName());
        fillField(By.id("last-name"), userData.getLastName());
        fillField(By.id("postal-code"), userData.getPostalCode());
    }

    private String getSummaryLabelValue(String label) {
        String xpath = String.format("//div[contains(text(), '%s')]/following-sibling::div[@class='summary_value_label']", label);
        return findElement(By.xpath(xpath)).getText();
    }

    private BigDecimal calculateTotalPrice() {
        List<WebElement> cartItems = findElements(By.cssSelector(".cart_item"));
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (WebElement cartItem : cartItems) {
            String priceText = cartItem.findElement(By.cssSelector(".inventory_item_price")).getText();
            BigDecimal itemPrice = parseItem(priceText);
            totalPrice = totalPrice.add(itemPrice);
        }

        return totalPrice;
    }

    private BigDecimal getPriceTotal(String cssSelector) {
        String actualItemTotalString = findElement(By.cssSelector(cssSelector)).getText().trim();
        String numericItemTotal = actualItemTotalString.replaceAll("[^\\d.]", "");

        BigDecimal subTotal = new BigDecimal(numericItemTotal);
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
        return subTotal;
    }

    private BigDecimal parseItem(String priceText) {
        return new BigDecimal(priceText.replace("$", ""));
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
