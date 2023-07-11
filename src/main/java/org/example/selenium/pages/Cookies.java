package org.example.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cookies extends GenericAbstractPage {

    @FindBy(id = "cookie-popup")
    WebElement cookiesPopup;

    @FindBy(id = "accept-cookies")
    WebElement acceptCookiesBtn;
    public void acceptCookiesIfNecessary(){
        waitShortForElementVisibility(cookiesPopup);
        if(cookiesPopup.isEnabled()){
//        if(cookiesPopup.isDisplayed()){
            acceptCookiesBtn.click();
        }
    }
}
