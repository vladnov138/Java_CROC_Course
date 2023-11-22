package org.example.Models;

import org.example.Exceptions.ModelNotFoundException;

/**
 * Модель товара для файла products.json
 *
 * @author Vladislav Novikov
 */
public class Product {
    public final int id;
    public final String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
