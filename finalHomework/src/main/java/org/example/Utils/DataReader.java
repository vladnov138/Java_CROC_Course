package org.example.Utils;

import com.google.gson.Gson;
import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Models.Saler;
import org.example.Models.StockProduct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataReader {
    private Gson gson;

    public DataReader() {
        gson = new Gson();
    }
    public Product[] readProducts(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        Product[] products = gson.fromJson(json, Product[].class);
        return products;
    }

    public Saler[] readSalers(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        Saler[] salers = gson.fromJson(json, Saler[].class);
        return salers;
    }

    public Sale[] readSales(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        Sale[] sales = gson.fromJson(json, Sale[].class);
        return sales;
    }

    public StockProduct[] stockProducts(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        StockProduct[] stockProducts = gson.fromJson(json, StockProduct[].class);
        return stockProducts;
    }
}
