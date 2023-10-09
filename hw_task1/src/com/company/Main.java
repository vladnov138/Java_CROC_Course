package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    /**
     * <p>
     * Возвращает результат проверки строки на палиндром (работает со знаками пунктуации .,?!:-)
     * </p>
     * @param text исходная строка для проверки на палиндром
     * @return результат проверки (true/false)
     */
    public static boolean isPalindrome(String text) {
        // Регулярным выражением убираем знаки препинания
        // (\\W (все, что не является словом) в Java убирает все русские символы, пришлось импровизировать)
        String expression = text.replaceAll("[.,?!:\\s-]", "");
        // Строку конвертируем в StringBuilder (StringBuilder - изменяемый тип для строки, у него есть
        // метод reverse(), у String его нет, т.к. String неизменяемый тип данных), реверсируем строку,
        // конвертируем в String через toString() и сравниваем строки через equalsIgnoreCase для независимого
        // от регистра сравнения
        return expression.equalsIgnoreCase(new StringBuilder(expression).reverse().toString());
    }

    public static void main(String[] args) {
        // Выводим пользователю, что требуется сделать
        System.out.println("Введите строку для проверки на палиндром: ");
        // Подключаем Scanner для чтения ввода
        Scanner in = new Scanner(System.in);
        // Считываем строку с ввода
        String text = in.nextLine();
        // Вызываем функцию isPalindrome и записываем результат в result
        boolean result = isPalindrome(text);
        // Выводим результат через тернарный оператор
        System.out.println(result ? "Палиндром" : "Не палиндром");
    }
}
