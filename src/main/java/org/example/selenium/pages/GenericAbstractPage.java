package org.example.selenium.pages;

import org.example.selenium.driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class GenericAbstractPage {

    protected final WebDriver webDriver;
    public long shortTimeOut = 10;
    public GenericAbstractPage() {
        this.webDriver = WebDriverProvider.getDriver();
        PageFactory.initElements(this.webDriver, this);
    }

    public void waitForElementVisibility(WebElement element, long timeOut) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.of(timeOut, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitShortForElementVisibility(WebElement element) {
        waitForElementVisibility(element, shortTimeOut);
    }

}
