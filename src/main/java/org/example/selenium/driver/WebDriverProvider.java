package org.example.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.selenium.pages.CookiesPage;
import org.example.selenium.pages.HomePage;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverProvider {

    static org.openqa.selenium.WebDriver webDriver;

    public static void setUpDriver(boolean headless) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\thoma\\Documents\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless");
            webDriver = new ChromeDriver(options);
        }

        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public static org.openqa.selenium.WebDriver getDriver() {
        return webDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public static HomePage goToURL(String url) {
        webDriver.get(url);
        new CookiesPage().selectLanguageIfNecessary().acceptCookiesIfNecessary();
        return new HomePage();
    }


}
