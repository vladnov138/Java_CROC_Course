package org.example.Models;

import java.util.Date;

/**
 * Модель продажи для файла sales.json
 *
 * @author Vladislav Novikov
 */
public record Sale (int id, int salesmanId, int productId, int salesCount, Date saleDate) {}
