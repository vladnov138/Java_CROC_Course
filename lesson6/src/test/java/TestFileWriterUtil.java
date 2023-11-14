import Utils.FileWriterUtil;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты класса {@link FileWriterUtil}
 *
 * @author Vladislav Novikov
 */
public class TestFileWriterUtil {
    /**
     * Тест метода {@link FileWriterUtil#writeCommentsToFile(String, List)}
     *
     * @throws IOException
     */
    @Test
    public void testWriteComments() throws IOException {
        // given:
        String filepath = "./src/test/resources/result.txt";
        ArrayList<String> comments = new ArrayList<>(List.of("comment1", "comment2"));

        // when:
        FileWriterUtil.writeCommentsToFile(filepath, comments);

        // then:
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                assertThat(line).isEqualTo(comments.get(i));
            }
        }
        Path path = Paths.get(filepath);
        Files.deleteIfExists(path);
    }
}
