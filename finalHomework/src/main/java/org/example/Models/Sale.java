package org.example.Models;

import java.util.Date;

/**
 * Модель продажи для файла sales.json
 *
 * @author Vladislav Novikov
 */
public class Sale {
    public final int id;
    public final int salerId;
    public final int productId;
    public final int salesNum;
    public final Date saleDate;

    public Sale(int id, int salerId, int productId, int salesNum, Date saleDate) {
        this.id = id;
        this.salerId = salerId;
        this.productId = productId;
        this.salesNum = salesNum;
        this.saleDate = saleDate;
    }
}
