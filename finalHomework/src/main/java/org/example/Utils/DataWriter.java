package org.example.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Класс для записи сериализованных JSON данных в файл
 *
 * @author Vladislav Novikov
 */
public class DataWriter {
    private final Gson gson;

    /**
     * Constructor
     */
    public DataWriter() {
        gson = new Gson();
    }

    /**
     * Запись JSON данных по указанному пути к файлу
     * @param jsonData сериализованные данные
     * @param filePath путь к файлу
     */
    public void writeDataToFile(JsonObject jsonData, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(jsonData, fileWriter);
        } catch (IOException e) {
            throw e;
        }
    }
}
