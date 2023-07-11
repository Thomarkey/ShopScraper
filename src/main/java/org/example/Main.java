package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
//    public static void main(String[] args) {
//        WebDriverProvider.setUpDriver();
//        WebDriverProvider.goToURL("https://www.ah.be/")
//                .lookFor("boter");
//
//        WebDriverProvider.quitDriver();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}