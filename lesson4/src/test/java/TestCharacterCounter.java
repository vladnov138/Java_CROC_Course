import org.example.CharacterCounter;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Тесты класса {@link CharacterCounter}.
 *
 * @author Vladislav Novikov
 */
public class TestCharacterCounter {
    /**
     * Тест метода {@link CharacterCounter#count(String)}
     * </p>
     * Проверяет правильность подсчета частоты появления символов
     * @throws IOException при ошибке теста
     */
    @Test
    public void testCount() throws IOException {
        // given:
        final String inPath = "./src/test/resources/testCount.txt";
        // when:
        final HashMap<Character, Integer> result = CharacterCounter.count(inPath);
        // then:
        assertThat(result).isNotNull();
        assertThat(result)
                .containsEntry('A', 3)
                .containsEntry('B', 3)
                .containsEntry('a', 1)
                .containsEntry('b', 1)
                .containsEntry('C', 1)
                .containsEntry('c', 1)
                .containsEntry('D', 1)
                .containsEntry('А', 1);
    }

    /**
     * Тест метода {@link CharacterCounter#writeCharFrequencyToFile(String, HashMap)}
     * </p>
     * Проверяет правильность генерации выходного файла
     * @throws IOException при ошибке теста
     */
    @Test
    public void testWriteCharFrequencyToFile() throws IOException {
        // given:
        final String outPath = "./src/test/resources/testWriteCharFrequencyToFile.txt";
        final HashMap<Character, Integer> data = new HashMap<>();
        data.put('A', 3);
        // when:
        CharacterCounter.writeCharFrequencyToFile(outPath, data);
        // then:
        try (BufferedReader reader = new BufferedReader(new FileReader(outPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assertThat(line).isEqualTo("A: 3");
            }
        }
        Path path = Paths.get(outPath);
        Files.deleteIfExists(path);
    }
}
