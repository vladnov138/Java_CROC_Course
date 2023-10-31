package org.example;

import java.io.*;
import java.util.HashMap;

public class CharacterCounter {

    public static HashMap<Character, Integer> count(String path) {
        File file = new File(path);
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
        return charCounter;
    }

    public static void outCharCounter(String path, HashMap<Character, Integer> charCounter) {
        File file = new File(path);
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
