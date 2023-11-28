import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.Utils.DataWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Тесты для класса {@link DataWriter}
 *
 * @author Vladislav Novikov
 */
public class TestDataWriter {
    private DataWriter dataWriter;
    private Gson gson;

    /**
     * Инициализация, запускаемая перед каждым тестом.
     */
    @BeforeEach
    protected void setUp() {
        gson = new Gson();
        dataWriter = new DataWriter();
    }

    /**
     * Тест метода {@link DataWriter#writeDataToFile(JsonObject, String)}
     * <p/>
     * Проверяет создание файла и правильность записи
     * @throws IOException при ошибке теста
     */
    @Test
    public void testWriteDataToFile() throws IOException {
        // given:
        final String filename = "./src/test/resources/test_result.json";
        JsonObject testData = new JsonObject();
        testData.addProperty("name", "John");
        testData.addProperty("age", 30);

        // when:
        dataWriter.writeDataToFile(testData, filename);

        // then:
        File file = new File(filename);
        Assertions.assertTrue(file.exists());
        String json = Files.readString(Paths.get(filename));
        JsonObject result = gson.fromJson(json, JsonObject.class);
        Assertions.assertEquals(testData, result);
        Files.deleteIfExists(Paths.get(filename));
    }
}
