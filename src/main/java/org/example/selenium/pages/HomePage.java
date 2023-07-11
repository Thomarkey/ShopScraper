package org.example.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends GenericAbstractPage {

    @FindBy(css = "#navigation-search-form input")
    WebElement searchBar;

    @FindBy(css = "#navigation-search-form [type=\"submit\"]")
    WebElement searchSubmit;


    public void lookFor(String product) {
        searchBar.sendKeys(product);
        searchSubmit.click();
        System.out.println(webDriver.getCurrentUrl());
    }
}
