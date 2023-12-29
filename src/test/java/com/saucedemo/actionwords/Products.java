package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.settings.ProjectSettings;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Products extends SeleniumHelper {

    public Products productIsDisplayed() {
        iSee("Products");
        return this;
    }

    public Products addsProductsToCart(List<String> productNames) {
        List<WebElement> productList = findElements(By.cssSelector(".inventory_item"));

        for (String productName : productNames) {
            WebElement product = findProductByName(productList, productName);
            if (product != null) {
                clickAddToCartButton(product);
            }
        }

        return this;
    }

    public ShoppingCart navigateToCart() {
        clickElement(By.id("shopping_cart_container"));
        return new ShoppingCart();
    }

    public Products validateProductInformation() {
        List<WebElement> productList = findElements(By.cssSelector(".inventory_item"));

        for (WebElement product : productList) {
            validateProductDetails(product);
            validateAddToCartButton(product);
        }

        return this;
    }

    private void validateProductDetails(WebElement product) {
        String productName = getElementText(product, ".inventory_item_name");
        String productDescription = getElementText(product, ".inventory_item_desc");
        String productPrice = getElementText(product, ".inventory_item_price");

        Map<String, String> productInfoMap = getProductInformation(productName)
                .orElseThrow(() -> new RuntimeException("Product information not found for: " + productName));

        assertEqual("Product name", productName, productInfoMap.get("productName"));
        assertEqual("Product description", productDescription, productInfoMap.get("productDescription"));
        assertEqual("Product price", productPrice, productInfoMap.get("productPrice"));
    }

    private void validateAddToCartButton(WebElement product) {
        WebElement addToCartButton = product.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
        assertTrue("Add to cart button not displayed", addToCartButton.isDisplayed());
    }

    private Optional<Map<String, String>> getProductInformation(String productNameToFilter) {
        String jsonData = new Utils().readFile(ProjectSettings.DATA_PATH + "productInfo.json");
        JSONObject json = new JSONObject(jsonData);
        JSONArray products = json.getJSONArray("products");

        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
            String productName = product.getString("productName");

            if (productName.equals(productNameToFilter)) {
                return Optional.of(Map.of(
                        "productName", productName,
                        "productDescription", product.getString("productDescription"),
                        "productPrice", product.getString("productPrice")
                ));
            }
        }
        return Optional.empty();
    }

    private WebElement findProductByName(List<WebElement> productList, String productName) {
        return productList.stream()
                .filter(product -> getElementText(product, ".inventory_item_name").equals(productName))
                .findFirst()
                .orElse(null);
    }

    private void clickAddToCartButton(WebElement product) {
        log("Clicking element: .//button[contains(text(), 'Add to cart')]");
        product.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]")).click();
    }

    private void assertTrue(String message, boolean condition) {
        Assert.assertTrue(message, condition);
    }

    private void assertEqual(String message, String expected, String actual) {
        Assert.assertEquals(message, expected, actual);
    }

    private String getElementText(WebElement element, String cssSelector) {
        try {
            String text = element.findElement(By.cssSelector(cssSelector)).getText();
            log("Successfully retrieved text using CSS selector '" + cssSelector + "': " + text);
            return text;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            log("Error while retrieving text using CSS selector '" + cssSelector + "': " + e.getMessage());
            throw e;
        }
    }

}
