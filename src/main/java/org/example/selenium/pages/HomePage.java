package org.example.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends GenericAbstractPage {

    //AH
    @FindBy(css = "#navigation-search-form input")
    WebElement searchBar;

    //AH
    @FindBy(css = "#navigation-search-form [type=\"submit\"]")
    WebElement searchSubmit;


    public SearchResultPage lookFor(String product) {
        waitShortForElementVisibility(searchBar);
        System.out.println("waited");
        searchBar.sendKeys(product);
        searchSubmit.click();
        return new SearchResultPage();
    }
}
