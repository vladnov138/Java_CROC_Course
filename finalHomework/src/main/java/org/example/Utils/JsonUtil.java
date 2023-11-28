package org.example.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;

import java.util.Map;

/**
 * Класс для конвертации данных в JSON формат
 *
 * @author Vladislav Novikov
 */
public class JsonUtil {
    private Gson gson;

    /**
     * Constructor
     */
    public JsonUtil() {
        gson = new Gson();
    }

    /**
     * Конвертация данных о средних продажах в день
     * @param meanSales среднее число продаж
     * @return JSON данные
     */
    public JsonObject convertMeanSales(double meanSales) {
        JsonObject result = new JsonObject();
        result.addProperty("mean_sales", meanSales);
        return result;
    }

    /**
     * Конвертация данных о товарах
     * @param products данные о товарах
     * @return JSON данные
     * @throws ModelNotFoundException если продукт не был найден по ID
     */
    public JsonObject convertTopProducts(Map<Integer, Integer> topProducts, Product[] products) throws ModelNotFoundException {
        JsonObject result = new JsonObject();
        JsonArray jsonProducts = new JsonArray();
        for (var entry : topProducts.entrySet()) {
            JsonObject jsonProduct = new JsonObject();
            int id = entry.getKey();
            jsonProduct.addProperty("id", id);
            jsonProduct.addProperty("name", Product.getProductById(products, id).name());
            jsonProduct.addProperty("sales_count", entry.getValue());
            jsonProducts.add(jsonProduct);
        }
        result.add("top", jsonProducts);
        return result;
    }
}
