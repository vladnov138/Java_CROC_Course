import Utils.FileReaderUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты класса {@link FileReaderUtil}
 *
 * @author Vladislav Novikov
 */
public class TestFileReaderUtil {

    /**
     * Тест метода {@link FileReaderUtil#readComments(String)}
     *
     * @throws IOException при ошибке теста
     */
    @Test
    public void testReadComments() throws IOException {
        // given:
        String filepath = "./src/test/resources/comments.txt";
        ArrayList<String> expected = new ArrayList<>(List.of("a", "b", "c", "d", "e"));

        // when:
        ArrayList<String> result = FileReaderUtil.readComments(filepath);

        // then:
        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);
    }

    /**
     * Тест метода {@link FileReaderUtil#readBlackList(String)}
     *
     * @throws IOException при ошибке теста
     */
    @Test
    public void testReadBlackList() throws IOException {
        // given:
        String filepath = "./src/test/resources/blacklist.txt";
        Set<String> expected = new HashSet<>(List.of("f", "a", "j", "e"));

        // when:
        Set<String> result = FileReaderUtil.readBlackList(filepath);

        // then:
        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);
    }
}
