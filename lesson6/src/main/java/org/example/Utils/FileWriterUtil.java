package org.example.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Запись комментариев в указанный файл
 *
 * @author Vladislav Novikov
 */
public class FileWriterUtil {
    /**
     * Запись комментариев в файл по указанному пути
     *
     * @param pathToFile путь к файлу
     * @param comments   комментарии
     * @throws IOException при ошибке записи в файл
     */
    public static void writeCommentsToFile(String pathToFile, List<String> comments) throws IOException {
        File file = new File(pathToFile);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (String comment :
                    comments) {
                out.write(comment + "\n");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
