package org.example.selenium.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class CookiesPage extends GenericAbstractPage {


    @FindBy(css = "[data-testid='language-modal-content']")
    WebElement languagePopup;

    @FindBy(css = "[data-testid='language-modal-content'] button")
    WebElement dutchLanguageBtn;

    @FindAll({
            @FindBy(id = "cookie-popup"), //AH
            @FindBy(css = "[data-testid='cookie-popup-description']"), //DELHAIZE

    })
    WebElement cookiesPopup;

    @FindAll({
            @FindBy(id = "accept-cookies"), //AH
            @FindBy(css = "[data-testid='cookie-popup-accept']"), //DELHAIZE

    })
    WebElement acceptCookiesBtn;


    public CookiesPage selectLanguageIfNecessary() {
        try {
            waitShortForElementVisibility(languagePopup);
            if (languagePopup.isEnabled()) {
//        if(languagePopup.isDisplayed()){
                dutchLanguageBtn.click();
            }
        } catch (TimeoutException ignored) {

        }
        return this;
    }

    public void acceptCookiesIfNecessary() {
        try {
            waitShortForElementVisibility(cookiesPopup);
            if (cookiesPopup.isEnabled()) {
//        if(cookiesPopup.isDisplayed()){
                acceptCookiesBtn.click();
            }
        } catch (TimeoutException ignored) {
        }
    }
}


