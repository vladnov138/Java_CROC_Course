import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест работы логики приложения
 *
 * @author Vladislav Novikov
 */
public class TestMain {
    /**
     * Исходный основной поток приложения.
     */
    private final PrintStream standardOut = System.out;

    /**
     * Поток, в который записывается вывод приложения в консоль.
     */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Инициализация, запускаемая перед каждым тестом.
     */
    @BeforeEach
    protected void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor, true, UTF_8));
    }

    /**
     * Восстановление, запускаемое после каждого теста.
     */
    @AfterEach
    protected void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Тест запуска приложения с существующим файлом
     */
    @Test
    public void testExistsFile() {
        // given:
        String inCommentPath = "./src/main/resources/comments.txt";
        String inBlackListPath = "./src/main/resources/comments.txt";
        String outPath = "./src/main/resources/output.txt";

        // when:
        Main.main(new String[]{inCommentPath, inBlackListPath, outPath});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8))
                .isEqualTo("Вывод выполнен по пути: " + outPath);
    }

    /**
     * Тест запуска приложения с несуществующим файлом
     */
    @Test
    public void testNotExistsFile() {
        // given:
        String inCommentPath = "./src/main/resources/inpsdut1.txt";
        String inBlackListPath = "./src/main/resources/blacklist.txt";
        String outPath = "./src/main/resources/output.txt";

        // when:
        Main.main(new String[]{inCommentPath, inBlackListPath, outPath});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo("Произошла ошибка при чтении файлов");
    }

    /**
     * Тест запуска приложения с неправильным количеством аргументов
     */
    @Test
    public void testInvalidArgs() {
        // given:

        // when:
        Main.main(new String[]{"input1.txt", "output.txt"});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8))
                .isEqualTo("Правильная передача аргументов: <comments path> " +
                        "<blacklist path> <out comments path>");
    }
}
