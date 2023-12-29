package com.saucedemo.utilities.base;


import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class UserData {
    private String firstName;
    private String lastName;
    private String postalCode;
    private String cardNumber;
    private String shippingInformation;
    private float tax;

    public UserData() {
        generateRandomUserData();
    }

    private void generateRandomUserData() {
        Faker faker = new Faker();

        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setPostalCode("Free Pony Express Delivery!");
        setCardNumber("SauceCard #31337");
        setShippingInformation("Free Pony Express Delivery!");
        setTax(7.68F);
    }
}
