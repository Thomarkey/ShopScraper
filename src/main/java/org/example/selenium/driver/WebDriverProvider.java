package org.example.selenium.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.selenium.pages.Cookies;
import org.example.selenium.pages.HomePage;

public class WebDriverProvider {

    static org.openqa.selenium.WebDriver webDriver;
//    private static ThreadLocal<org.openqa.selenium.WebDriver> driver = new ThreadLocal<>();
//    private static ThreadLocal<EventFiringWebDriver> eventHandlerDriver = new ThreadLocal<>();



    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\thoma\\Documents\\chromedriver.exe");
        webDriver = new ChromeDriver();
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
        new Cookies().acceptCookiesIfNecessary();
        return new HomePage();
    }


}
