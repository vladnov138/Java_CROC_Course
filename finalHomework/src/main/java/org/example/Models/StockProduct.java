package org.example.Models;

/**
 * Модель количества товара для файла stockproducts.json
 *
 * @author Vladislav Novikov
 */
public class StockProduct {
    public final int salerId;
    public final int productId;
    public final double cost;
    public final int productNum;

    public StockProduct(int salerId, int productId, double cost, int productNum) {
        this.salerId = salerId;
        this.productId = productId;
        this.cost = cost;
        this.productNum = productNum;
    }
}
