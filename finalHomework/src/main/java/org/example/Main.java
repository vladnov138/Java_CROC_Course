package org.example;

import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Utils.DataReader;
import org.example.Utils.DataWriter;
import org.example.Utils.JsonConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Sale[] sales;
        Product[] products;
        try {
            DataReader dataReader = new DataReader();
            products = dataReader.readProducts("./src/main/resources/products.json");
            sales = dataReader.readSales("./src/main/resources/sales.json");
        } catch (IOException e) {
            System.out.println("Error " + e);
            return;
        }
        JsonConverter jsonConverter = new JsonConverter();
        try {
            var res = Main.getTop(sales, products);
            DataWriter dataWriter = new DataWriter();
            dataWriter.writeDataToFile(jsonConverter.convertProducts(res),
                    "./src/main/resources/res.json");
            var meanSales = Main.getMeansalesCount(sales);
            System.out.println(meanSales);
            dataWriter.writeDataToFile(jsonConverter.convertMeanSales(meanSales),
                    "./src/main/resources/res2.json");
        } catch (ModelNotFoundException e) {
            System.out.println("Error + e");
            return;
        }
    }

    /**
     * Выборка топ 5 товаров по количеству проданных товаров
     * @param sales данные о продажах
     * @param products данные о товарах
     * @return топ 5 продуктов
     * @throws ModelNotFoundException товар не существует в перечне товаров
     */
    private static Product[] getTop(Sale[] sales, Product[] products) throws ModelNotFoundException {
        var productSales = new HashMap<Integer, Integer>();
        for (Sale sale :
                sales) {
            int productId = sale.productId();
            int salesCount = sale.salesCount() + productSales.getOrDefault(productId, 0);
            productSales.put(productId, salesCount);
        }
        var sortedProductSales =
                productSales.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .toList();
        Product[] result = new Product[sortedProductSales.size()];
        for (int i = 0; i < sortedProductSales.size(); i++) {
            result[i] = Product.getProductById(products, sortedProductSales.get(i).getKey());
        }
        return result;
    }

    /**
     * Подсчитывает среднее количество проданных товаров за день
     * @param sales данные о продажах
     * @return среднее количество проданных товаров за каждый день
     */
    private static Map<String, Double> getMeansalesCount(Sale[] sales) {
        final var salesByDate = new HashMap<Date, IntSummaryStatistics>();
        for (Sale sale:
             sales) {
            final Date saleDate = sale.saleDate();
            final var salesStats = salesByDate.getOrDefault(saleDate, new IntSummaryStatistics());
            salesStats.accept(sale.salesCount());
            salesByDate.put(saleDate, salesStats);
        }
        var keySets = salesByDate.keySet();
        final Map<String, Double> result = new HashMap<>();

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (Date saleDate :
             keySets) {
            var salesStats = salesByDate.get(saleDate);
            result.put(simpleDateFormat.format(saleDate), salesStats.getAverage());
        }
        return result;
    }
}
