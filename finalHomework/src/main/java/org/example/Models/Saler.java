package org.example.Models;

/**
 * Модель продавца для файла salers.json
 *
 * @author Vladislav Novikov
 */
public class Saler {
    public final int id;
    public final String firstName;
    public final String lastName;

    public Saler(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
