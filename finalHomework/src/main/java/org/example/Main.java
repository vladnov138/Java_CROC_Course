package org.example;

import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Utils.DataReader;
import org.example.Utils.DataWriter;
import org.example.Utils.JsonUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String productsPath = "./src/main/resources/products.json";
        String salesmenPath = "./src/main/resources/salesmen.json";
        String salesPath = "./src/main/resources/sales.json";
        String stockProductsPath = "./src/main/resources/stockProducts.json";
        String outDir = "./src/main/resources/";
        if (args.length == 5) {
            productsPath = args[0];
            salesmenPath = args[1];
            salesPath = args[2];
            stockProductsPath = args[3];
            outDir = args[4];
        } else if (args.length != 0) {
            System.out.print("Правильная передача аргументов: <products path> <salesmen path> <sales path> " +
                    "<stockproducts path> <out directory>");
            return;
        }

        Sale[] sales;
        Product[] products;
        try {
            DataReader dataReader = new DataReader();
            products = dataReader.readProducts(productsPath);
            sales = dataReader.readSales(salesPath);
        } catch (IOException e) {
            System.out.println("Error: " + e);
            return;
        }

        JsonUtil jsonConverter = new JsonUtil();
        try {
            Product[] res = getTop(sales, products);
            DataWriter dataWriter = new DataWriter();
            try {
                dataWriter.writeDataToFile(jsonConverter.convertTopProducts(res),
                        outDir + "/topProducts.json");
                Map<String, Double> meanSales = getMeansalesCount(sales);
                System.out.println(meanSales);
                dataWriter.writeDataToFile(jsonConverter.convertMeanSales(meanSales),
                        outDir + "/Meansales.json");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Выборка топ 5 товаров по количеству проданных товаров
     *
     * @param sales    данные о продажах
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
     *
     * @param sales данные о продажах
     * @return среднее количество проданных товаров за каждый день
     */
    private static Map<String, Double> getMeansalesCount(Sale[] sales) {
        final var salesByDate = new HashMap<Date, IntSummaryStatistics>();
        for (Sale sale :
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
