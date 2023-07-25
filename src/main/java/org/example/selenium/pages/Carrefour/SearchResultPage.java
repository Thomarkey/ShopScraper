package org.example.selenium.pages.Carrefour;

import org.example.api.models.Item;
import org.example.selenium.pages.GenericAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends GenericAbstractPage {

    @FindBy(css = ".product__listing .product-item")
    List<WebElement> products;

    @FindBy(css = ".product__listing .product-item")
    WebElement firstProduct;

    public List<Item> getProducts() {
        //TODO: fix waitShortForElementsVisibility(products.get(0));
        waitShortForElementVisibility(firstProduct);
        List<Item> itemsList = new ArrayList<>();

        for (WebElement product : products) {
            // Extract specific data from the element
            String title = product.findElement(By.cssSelector(".name-title")).getText();
            String price =  product.findElement(By.cssSelector(".priceinfo .baseprice")).getText();
            String size =  product.findElement(By.cssSelector(".priceinfo .txt-label")).getText();
            String imageUrl = product.findElement(By.cssSelector("img")).getAttribute("src");

            String pricePerKg =  product.findElement(By.cssSelector(("#price"))).getText();

            itemsList.add(new Item(title, price, size, imageUrl, pricePerKg));
        }

        return itemsList;

    }


}
