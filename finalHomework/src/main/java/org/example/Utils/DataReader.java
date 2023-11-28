package org.example.Utils;

import com.google.gson.Gson;
import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Models.Salesman;
import org.example.Models.StockProduct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс, считывающий данные JSON
 *
 * @author Vladislav Novikov
 */
public class DataReader {
    private final Gson gson;

    /**
     * Constructor
     */
    public DataReader() {
        gson = new Gson();
    }

    /**
     * Чтение файла с продуктами
     * @param filename путь к файлу
     * @return десериализованные данные
     * @throws IOException при ошибке чтения файла
     */
    public Product[] readProducts(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        return gson.fromJson(json, Product[].class);
    }

    /**
     * Чтение файла с продавцами
     * @param filename путь к файлу
     * @return десериализованные данные
     * @throws IOException при ошибке чтения файла
     */
    public Salesman[] readSalesmen(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        return gson.fromJson(json, Salesman[].class);
    }

    /**
     * Чтение файла с продажами
     * @param filename путь к файлу
     * @return десериализованные данные
     * @throws IOException при ошибке чтения файла
     */
    public Sale[] readSales(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        return gson.fromJson(json, Sale[].class);
    }

    /**
     * Чтение файла с запасами продуктов у продавцов
     * @param filename путь к файлу
     * @return десериализованные данные
     * @throws IOException при ошибке чтения файла
     */
    public StockProduct[] stockProducts(String filename) throws IOException {
        String json = Files.readString(Paths.get(filename));
        return gson.fromJson(json, StockProduct[].class);
    }
}
