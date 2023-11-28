package org.example.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
     */
    public JsonObject convertTopProducts(Product[] products) {
        JsonObject result = new JsonObject();
        JsonArray jsonProducts = new JsonArray();
        for (Product product : products) {
            JsonObject jsonProduct = new JsonObject();
            jsonProduct.addProperty("id", product.id());
            jsonProduct.addProperty("name", product.name());
            jsonProducts.add(jsonProduct);
        }
        result.add("top", jsonProducts);
        return result;
    }
}
