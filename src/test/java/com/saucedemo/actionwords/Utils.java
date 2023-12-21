package com.saucedemo.actionwords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {

    public String readFile(String filePath) {
        try {
            byte[] encoded = Files.readAllBytes(Path.of(filePath));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}