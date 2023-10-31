package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Считает символы в указанном файле и выводит отсортированный результат в указанный файл
 *
 * @author Vladislav Novikov
 */
public class CharacterCounter {

    /**
     * Считает кол-во символов в указанном файле (не считая пробелы и переносы строк)
     *
     * @param inPath путь к файлу с символами
     * @return HashMap c частотой повторения символов
     */
    public static HashMap<Character, Integer> count(String inPath) throws IOException {
        File file = new File(inPath);
        HashMap<Character, Integer> charCounter = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int characterCode; // при считывании посимвольно мы получаем код символа
            while ((characterCode = in.read()) != -1) {
                char character = (char) characterCode; // переводим в char
                if (character == '\s' || character == '\n' || character == '\r') { // пропускаем все лишнее
                    continue;
                }
                if (charCounter.containsKey(character)) {
                    charCounter.put(character, charCounter.get(character) + 1);
                } else {
                    charCounter.put(character, 1);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return charCounter;
    }

    /**
     * Записывает HashMap с частотой повторения каждого символа в указанный файл
     *
     * @param outPath     путь к файлу для вывода
     * @param charCounter HashMap с частотой повторения каждого символа
     */
    public static void writeCharFrequencyToFile(String outPath, HashMap<Character, Integer> charCounter)
            throws IOException {
        File file = new File(outPath);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (char character :
                    charCounter.keySet()) {
                out.write(character + ": " + charCounter.get(character) + "\n");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
