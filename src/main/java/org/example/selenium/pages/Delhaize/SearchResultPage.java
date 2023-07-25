package org.example.selenium.pages.Delhaize;

import org.example.api.models.Item;
import org.example.selenium.pages.GenericAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


//DELHAIZE
public class SearchResultPage extends GenericAbstractPage {

    @FindBy(css = "[data-testid='product-block']")
    List<WebElement> products;

    @FindBy(css = "[data-testid='product-block']")
    WebElement firstProduct;

    public List<Item> getProducts() {
        //TODO: fix waitShortForElementsVisibility(products.get(0));
        waitShortForElementVisibility(firstProduct);
        List<Item> itemsList = new ArrayList<>();

        for (WebElement product : products) {
            // Extract specific data from the element
            String title = product.findElement(By.cssSelector("[data-testid='product-block-name-link']")).getText();
            String price =  product.findElement(By.cssSelector("[data-testid='product-block-price']")).getText();
            String size =  product.findElement(By.cssSelector("[data-testid='product-block-supplementary-price-2']")).getText();
            String imageUrl = product.findElement(By.cssSelector("img")).getAttribute("src");

            String pricePerKg =  product.findElement(By.cssSelector(("[data-testid='product-block-price-per-unit']"))).getText();

            itemsList.add(new Item(title, price, size, imageUrl, pricePerKg));
        }

        return itemsList;

    }


}
