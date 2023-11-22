package org.example;

import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Utils.DataReader;

import java.io.IOException;
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
        try {
            var res = Main.getTop(sales, products);
            for (var it :
                    res) {
                System.out.println(it.name);
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Error + e");
            return;
        }
        System.out.println(Main.getMeanSalesNum(sales));
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
            int productId = sale.productId;
            int salesNum = sale.salesNum;
            if (productSales.containsKey(productId)) {
                salesNum += productSales.get(productId);
            }
            productSales.put(productId, salesNum);
        }
        var sortedProductSales =
                productSales.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(5).toList();
        Product[] result = new Product[5];
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
    private static Map<Date, Double> getMeanSalesNum(Sale[] sales) {
        final var salesByDate = new HashMap<Date, ArrayList<Integer>>();
        for (Sale sale:
             sales) {
            final Date saleDate = sale.saleDate;
            final ArrayList<Integer> salesNums;
            if (salesByDate.containsKey(saleDate)) {
                salesNums = salesByDate.get(saleDate);
            } else {
                salesNums = new ArrayList<>();
            }
            salesNums.add(sale.salesNum);
            salesByDate.put(saleDate, salesNums);
        }
        var keySets = salesByDate.keySet();
        final Map<Date, Double> result = new HashMap<>();
        for (Date saleDate :
             keySets) {
            var salesNums = salesByDate.get(saleDate);
            result.put(saleDate, salesNums.stream().mapToDouble(it -> it).sum() / salesNums.size());
        }
        return result;
    }
}
