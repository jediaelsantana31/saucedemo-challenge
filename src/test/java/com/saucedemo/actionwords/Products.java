package com.saucedemo.actionwords;

import com.saucedemo.utilities.base.SeleniumHelper;
import com.saucedemo.utilities.settings.ProjectSettings;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Products extends SeleniumHelper {

    public void validateProductScreenDisplayed() {
        iSeeTitle("Swag Labs", 3);
    }

    public void validateProductInformation() {
        List<WebElement> productList = findElements(By.cssSelector(".inventory_item"));

        for (WebElement product : productList) {
            String productName = product.findElement(By.cssSelector(".inventory_item_name")).getText();
            String productDescription = product.findElement(By.cssSelector(".inventory_item_desc")).getText();
            String productPrice = product.findElement(By.cssSelector(".inventory_item_price")).getText();

            Map<String, String> productInfoMap = getProductInformation(productName)
                    .orElseThrow(() -> new RuntimeException("Product information not found for: " + productName));

            Assert.assertEquals("Product name mismatch for: " + productName,
                    productName, productInfoMap.get("productName"));
            Assert.assertEquals("Product description mismatch for: " + productName,
                    productDescription, productInfoMap.get("productDescription"));
            Assert.assertEquals("Product price mismatch for: " + productName,
                    productPrice, productInfoMap.get("productPrice"));

            WebElement addToCartButton = product.findElement(By.xpath(
                    ".//button[contains(text(), 'Add to cart')]"));
            Assert.assertTrue("Add to cart button not displayed for: " + productName,
                    addToCartButton.isDisplayed());
        }
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

}
