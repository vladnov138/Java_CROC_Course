package com.company;

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
        // метод reverse(), у String его нет, т.к. String неизменяемый тип данных)
        return expression.equalsIgnoreCase(new StringBuilder(expression).reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println("Input the line for palindrome checking: ");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        boolean result = isPalindrome(text);
        System.out.println(result ? "Palindrome" : "Not palindrome");
    }
}
