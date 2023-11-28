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
public class JsonConverter {
    private Gson gson;

    /**
     * Constructor
     */
    public JsonConverter() {
        gson = new Gson();
    }

    /**
     * Конвертация данных о средних продажах в день
     * @param meanSales данные о средних продажах в день
     * @return JSON данные
     */
    public JsonObject convertMeanSales(Map<String, Double> meanSales) {
        return gson.toJsonTree(meanSales).getAsJsonObject();
    }

    /**
     * Конвертация данных о товарах
     * @param products данные о товарах
     * @return JSON данные
     */
    public JsonObject convertProducts(Product[] products) {
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
