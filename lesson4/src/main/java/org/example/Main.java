package org.example;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Character, Integer> charCounter = CharacterCounter.count("./src/main/resources/input.txt");
        for (char character :
                charCounter.keySet()) {
            System.out.println(character + ": " + charCounter.get(character));
        }
    }
}