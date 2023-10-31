package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    /**
     * Сортирует HashMap по значению (частоте повторения символов) по стандартному алгоритму (Java)
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
        String inPath = "./src/main/resources/input.txt";
        String outPath = "./src/main/resources/output.txt";
        if (args.length == 2) { // Если нам передали наши аргументы, то значит это наши пути к файлам
            inPath = args[0];
            outPath = args[1];
        } else if (args.length > 0) { // А если больше двух, пользователь хочет что-то непонятное
            System.out.print("Правильная передача аргументов: <inPath> <outPath>");
            return;
        }

        try {
            HashMap<Character, Integer> charCounter = CharacterCounter.count(inPath);
            try {
                CharacterCounter.writeCharFrequencyToFile(
                        outPath, sortHashMap(charCounter));
                System.out.print("Вывод выполнен по пути: " + outPath);
            } catch (IOException e) {
                System.out.print("Произошла ошибка при записи файла");
            }
        } catch (IOException e) {
            System.out.print("Произошла ошибка при чтении файла");
        }
    }
}