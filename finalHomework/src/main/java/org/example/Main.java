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
            System.out.print("Valid arguments order: <products path> <salesmen path> <sales path> " +
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
            System.out.print("Reading Error. Make sure that files are exists.");
            return;
        }

        JsonUtil jsonConverter = new JsonUtil();
        try {
            Product[] res = getTop(sales, products);
            DataWriter dataWriter = new DataWriter();
            try {
                dataWriter.writeDataToFile(jsonConverter.convertTopProducts(res),
                        outDir + "topProducts.json");
                double meanSales = getMeansalesCount(sales);
                dataWriter.writeDataToFile(jsonConverter.convertMeanSales(meanSales),
                        outDir + "Meansales.json");
                System.out.print("Result saved in: " + outDir + ", files: topProducts.json, Meansales.json");
            } catch (IOException e) {
                System.out.print("Output error");
            }
        } catch (ModelNotFoundException e) {
            System.out.print("Model not found. Make sure that models ids are valid");
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
    private static double getMeansalesCount(Sale[] sales) {
        double s = 0;
        double counter = 0;
        var passedDates = new HashSet<Date>();
        for (Sale sale :
                sales) {
            s += sale.salesCount();
            Date saleDate = sale.saleDate();
            if (!passedDates.contains(saleDate)) {
                passedDates.add(saleDate);
                counter++;
            }
        }
        return s / counter;
    }
}
