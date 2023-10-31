package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
}
