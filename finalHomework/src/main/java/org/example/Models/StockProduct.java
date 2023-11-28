package org.example.Models;

/**
 * Модель количества товара для файла stockproducts.json
 *
 * @author Vladislav Novikov
 */

public record StockProduct(int salesmanId, int productId, double cost, int productCount) {}
