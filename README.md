﻿# ShopScraper

Shop Scraper is a web application that allows users to search for products on different online stores and compare their prices. It scrapes product data from various online stores and presents the results to the user.

## Features

- Search for products: Users can enter a search term and select a store (Delhaize, Albert Heijn, Carrefour) to find relevant products.

- Product Comparison: The app displays a list of products with their titles, prices, sizes, and images. Users can compare prices across different stores.

## Prerequisites

- Node.js (version XX.XX.XX)
- Angular CLI (version XX.XX.XX)
- Java Development Kit (JDK) (version XX)
- Maven (version XX)

## Installation

1. Clone the repository.

2. Frontend Setup:
   - Open a terminal and navigate to the `frontend` directory.
   - Run `npm install` to install the required dependencies.

3. Backend Setup:
   - Open a terminal and navigate to the `backend` directory.
   - Run `mvn clean install` to build the backend project and resolve dependencies.

## Usage

1. Start the Backend Server:
   - Open a terminal and navigate to the `backend` directory.
   - Run `java -jar target/shop-scraper-1.0.0.jar` to start the backend server.

2. Start the Frontend Development Server:
   - Open a terminal and navigate to the `frontend` directory.
   - Run `ng serve` to start the frontend development server.

3. Open the Application:
   - Open your web browser and go to `http://localhost:4200/` to access the application.

4. Search for Products:
   - Enter a search term in the input field and select the store (Delhaize, Albert Heijn, Carrefour) from the checkboxes.
   - Click the "Submit" button to fetch products from the selected store.

## Technologies Used

- Frontend: Angular, HTML, CSS
- Backend: Java, Spring Boot
- Web Scraping: Jsoup (for parsing HTML content)
