package org.example.Models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.Exceptions.ModelNotFoundException;

/**
 * Модель товара для файла products.json
 *
 * @author Vladislav Novikov
 */
public record Product(int id, String name) {
    /**
     * Поиск товара по его ID
     * @param products перечень товаров
     * @param productId ID искомого товара
     * @return товар
     * @throws ModelNotFoundException при отсутствии товара с искомым ID
     */
    public static Product getProductById(Product[] products, int productId) throws ModelNotFoundException {
        for (Product product :
                products) {
            if (product.id == productId) {
                return product;
            }
        }
        throw new ModelNotFoundException();
    }
}
