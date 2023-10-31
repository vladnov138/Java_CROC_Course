package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    /**
     * Сортирует HashMap по значению (частоте повторения символов)
     *
     * @param charCounter HashMap с частотой повторения каждого символа
     * @return отсортированный LinkedHashMap по частоте повторения каждого символа
     */
    private static LinkedHashMap<Character, Integer> sortHashMap(HashMap<Character, Integer> charCounter) {
        var list = new ArrayList<>(charCounter.entrySet()); // берем множество Entry и делаем из него ArrayList
        list.sort(Map.Entry.comparingByValue()); // Сортируем встроенным алгоритмом
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        for (var entry :
                list) {  // Заполняем новый LinkedHashMap (чтобы порядок сохранился)
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        String outPath = "./src/main/resources/output.txt";
        String inPath = "./src/main/resources/input.txt";
        try {
            HashMap<Character, Integer> charCounter = CharacterCounter.count(inPath);
            try {
                CharacterCounter.writeCharFrequencyToFile(
                        outPath, sortHashMap(charCounter));
            } catch (IOException e) {
                System.out.println("Произошла ошибка при записи файла: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
        }
        System.out.println("Вывод выполнен по пути: " + outPath);
    }
}