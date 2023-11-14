package org.example.Utils;

import java.io.*;
import java.util.*;

/**
 * Чтение комментариев и черного списка из заданных файлов
 *
 * @author Vladislav Novikov
 */
public class FileReaderUtil {
    /**
     * Чтение комментариев из заданного файла
     *
     * @param pathToFile путь к файлу с комментариями
     * @return Список комментариев
     * @throws IOException при ошибке чтения файла
     */
    public static ArrayList<String> readComments(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        ArrayList<String> comments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                comments.add(line);
            }
        } catch (IOException e) {
            throw e;
        }
        return comments;
    }

    /**
     * Чтение черного листа из заданного файла
     *
     * @param pathToFile путь к файлу
     * @return Множество с ключевыми словами
     * @throws IOException при ошибке чтения файла
     */
    public static Set<String> readBlackList(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        HashSet<String> blackList = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                blackList.addAll(List.of(line.split("\\s")));
            }
        } catch (IOException e) {
            throw e;
        }
        return blackList;
    }
}
