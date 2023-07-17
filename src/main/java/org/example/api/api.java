package org.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.models.Item;
import org.example.selenium.driver.WebDriverProvider;
import org.example.selenium.pages.HomePage;
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

//
//    @GetMapping("/{searchTerm}")
//    public String lookUp(@PathVariable String searchTerm) throws IOException {
//
//        String url = "https://www.ah.be/zoeken?query=" + searchTerm;
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
//            // Extract information using CSS selectors
//            Elements elements = document.select("#search-lane [data-testhook='product-card']");
//
//            // Process the extracted elements
//            for (Element element : elements) {
//                // Extract specific data from the element
//                String title = element.select("[data-testhook='product-title']").text();
//                String price = element.select("[data-testhook='price-amount']").text();
//                String size = element.select("[data-testhook='product-unit-size']").text();
//                String imageUrl = element.select("img").attr("src");
//                itemsList.add(new Item(title, price, size, imageUrl, null));
//            }
//
//            connection.disconnect();
//        }
//        System.out.println(itemsList);
//
//
//        return objectMapper.writeValueAsString(itemsList);
//    }

    public String lookUpAH(@PathVariable String searchTerm)  {


        WebDriverProvider.setUpDriver();
        WebDriverProvider.goToURL("https://www.ah.be/zoeken?query=");

        HomePage homePage = new HomePage();

        homePage.lookFor(searchTerm);

        System.out.println("selenium done");

        //TODO: add selectors to pages

//             Extract information using CSS selectors
//            Elements elements = document.select("#search-lane [data-testhook='product-card']");
//
//            // Process the extracted elements
//            for (Element element : elements) {
//                // Extract specific data from the element
//                String title = element.select("[data-testhook='product-title']").text();
//                String price = element.select("[data-testhook='price-amount']").text();
//                String size = element.select("[data-testhook='product-unit-size']").text();
//                String imageUrl = element.select("img").attr("src");
//                //no pricePerKg shown
//                itemsList.add(new Item(title, price, size, imageUrl, null));
//            }
//
//
//        return objectMapper.writeValueAsString(itemsList);
        WebDriverProvider.quitDriver();
        return null;
    }

    public String lookUpDelhaize(@PathVariable String searchTerm) throws IOException {

//        String url = "https://www.delhaize.be/shop/search?q=" + searchTerm;
//        String url = "https://www.delhaize.be/shop/search?text=" + searchTerm;
        String url = "https://www.delhaize.be/shop/search?q=" + searchTerm + "%3Arelevance&text=" + searchTerm +"&sort=relevance";

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

            // Extract information using CSS selectors
            Elements elements = document.select("[data-testid='search-results-list-wrapper'] .product-item");

            // Process the extracted elements
            for (Element element : elements) {
                // Extract specific data from the element
                String title = element.select("[data-testid='product-block-name-link']").text();
                String price = element.select("[data-testid='product-block-price']").text();
                String size = element.select("[data-testid='product-block-supplementary-price-2']").text();
                String imageUrl = element.select("img").attr("src");
                //there is a pricePerKg here
                String pricePerKg = element.select("[data-testid='product-block-price-per-unit']").text();
                itemsList.add(new Item(title, price, size, imageUrl, pricePerKg));
            }

            connection.disconnect();
        }
        System.out.println(itemsList);


        return objectMapper.writeValueAsString(itemsList);
    }

    @GetMapping("/{searchTerm}/{store}")
    public String lookUpWithStore(@PathVariable String searchTerm, @PathVariable String store) throws IOException {
        return switch (store) {
            case "AH" -> lookUpAH(searchTerm);
            case "Delhaize" -> lookUpDelhaize(searchTerm);
            default -> null;
        };
    }
}

