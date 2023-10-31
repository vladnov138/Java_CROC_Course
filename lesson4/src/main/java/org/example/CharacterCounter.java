package org.example;

import java.io.*;
import java.util.*;

public class CharacterCounter {

    public static LinkedHashMap<Character, Integer> count(String inPath) {
        File file = new File(inPath);
        HashMap<Character, Integer> charCounter = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int charactedCode;
            while ((charactedCode = in.read()) != -1) {
                char character = (char) charactedCode;
                if (character == '\s' || character == '\n' || character == '\r')
                    continue;
                if (charCounter.containsKey(character)) {
                    charCounter.put(character, charCounter.get(character) + 1);
                } else {
                    charCounter.put(character, 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sortHashmap(charCounter);
    }

    private static LinkedHashMap<Character, Integer> sortHashmap(HashMap<Character, Integer> charCounter) {
        var list = new ArrayList<>(charCounter.entrySet());
        list.sort(Map.Entry.comparingByValue());
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        for (var entry :
                list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void outCharCounter(String outPath, HashMap<Character, Integer> charCounter) {
        File file = new File(outPath);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (char character :
                    charCounter.keySet()) {
                out.write(character + ": " + charCounter.get(character) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
