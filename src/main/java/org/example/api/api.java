package org.example.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.models.Item;
import org.example.selenium.driver.WebDriverProvider;
import org.example.selenium.pages.Delhaize.SearchResultPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://localhost:4200", "http://localhost:4200/"})
public class Api {

    private final ObjectMapper objectMapper;

    public Api(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{searchTerm}/{store}")
    public String lookUpWithStore(@PathVariable String searchTerm, @PathVariable String store) throws IOException {
        return switch (store) {
            case "AH" -> lookUpAH(searchTerm);
            case "Delhaize" -> lookUpDelhaize(searchTerm);
            case "Carrefour" -> lookUpCarrefour(searchTerm);
            default -> null;
        };
    }


    //SELENIUM because result page is rendered in iframe
    public String lookUpDelhaize(@PathVariable String searchTerm) throws JsonProcessingException {
        WebDriverProvider.setUpDriver(true);
        WebDriverProvider.goToURL("https://www.delhaize.be/shop/search?text=" + searchTerm);


        List<Item> itemsList = new SearchResultPage().getProducts();
        WebDriverProvider.quitDriver();
        return objectMapper.writeValueAsString(itemsList);
    }

    //API CALL
    public String lookUpAH(@PathVariable String searchTerm) throws IOException {

//        String url = "https://www.delhaize.be/shop/search?q=" + searchTerm;
//        String url = "https://www.delhaize.be/shop/search?text=" + searchTerm;
        String url = "https://www.ah.be/zoeken?query=" + searchTerm;

        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();

        int responseCode = connection.getResponseCode();

        List<Item> itemsList = new ArrayList<>();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;


            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the HTML response
            Document document = Jsoup.parse(response.toString());

//             Extract information using CSS selectors
            Elements elements = document.select("#search-lane [data-testhook='product-card']");

            // Process the extracted elements
            for (Element element : elements) {
                // Extract specific data from the element
                String title = element.select("[data-testhook='product-title']").text();
                String price = element.select("[data-testhook='price-amount']").text();
                String size = element.select("[data-testhook='product-unit-size']").text();
                String imageUrl = element.select("img").attr("src");
                //no pricePerKg shown
                itemsList.add(new Item(title, price, size, imageUrl, null));
            }

            connection.disconnect();
        }
        System.out.println(itemsList);


        return objectMapper.writeValueAsString(itemsList);
    }

//    public String lookUpCarrefour(@PathVariable String searchTerm) throws IOException {
//
//        String url = "https://drive.carrefour.be/nl/search?text=" + searchTerm;
//
//        URL apiUrl = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
//        connection.setRequestMethod("GET");
//
//        StringBuilder response = new StringBuilder();
//
//        int responseCode = connection.getResponseCode();
//
//        List<Item> itemsList = new ArrayList<>();
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//
//
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//
//            // Parse the HTML response
//            Document document = Jsoup.parse(response.toString());
//
////             Extract information using CSS selectors
//            Elements elements = document.select(".product__listing .product-item");
//
//            // Process the extracted elements
//            for (Element element : elements) {
//                // Extract specific data from the element
//                String title = element.select(".name-title").text();
//                String price = element.select(".priceinfo .baseprice").text();
//                String size = element.select(".priceinfo .txt-label").text();
//                //TODO: fix lazy loading for images while scraping
//                String imageUrl = element.select("img").attr("src");
//                String pricePerKg = element.select("#price").text();
//                itemsList.add(new Item(title, price, size, imageUrl,  pricePerKg));
//            }
//
//            connection.disconnect();
//        }
//        System.out.println(itemsList);
//
//
//        return objectMapper.writeValueAsString(itemsList);
//
//    }

    //SELENIUM because of lazy loading images
    public String lookUpCarrefour(@PathVariable String searchTerm) throws JsonProcessingException {
        WebDriverProvider.setUpDriver(true);
        WebDriverProvider.goToURL("https://drive.carrefour.be/nl/search?text=" + searchTerm);


        List<Item> itemsList = new org.example.selenium.pages.Carrefour.SearchResultPage().getProducts();
        WebDriverProvider.quitDriver();
        return objectMapper.writeValueAsString(itemsList);
    }
}

