package org.example.api.models;


import org.example.api.Helper;

public class Item {

    private String title;
    private String price;

    private String size;

    private double pricePerKg;

    private String imageUrl;

    public Item(String title, String price, String size, String imageUrl, String pricePerKg) {
        this.title = title;
        this.price = price;
        this.size = size;
        if (pricePerKg == null) {
            this.pricePerKg = calculatePricePerKg();
        } else {
            this.pricePerKg = Helper.parseSizeNumber(pricePerKg);
        }
        this.imageUrl = imageUrl;
    }


    //TODO: adjust for cl,litre etc
    public double calculatePricePerKg() {
        if (!this.size.contains("kg")) {
            double numericSize = Helper.parseSizeNumber(this.size);
            double numericPrice = Helper.parseNumber(this.price);

            this.pricePerKg = numericPrice / (numericSize / 1000);
        }
        return Math.round(this.pricePerKg * 100.0) / 100.0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}
