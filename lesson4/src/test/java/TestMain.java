import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты проверки логики приложения
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

        // when:
        Main.main(new String[]{"./src/main/resources/input.txt", "./src/main/resources/output.txt"});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8))
                .isEqualTo("Вывод выполнен по пути: ./src/main/resources/output.txt");
    }

    /**
     * Тест запуска приложения с несуществующим файлом
     */
    @Test
    public void testNotExistsFile() {
        // given:

        // when:
        Main.main(new String[]{"./src/main/resources/input1.txt", "./src/main/resources/output.txt"});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo("Произошла ошибка при чтении файла");
    }

    /**
     * Тест запуска приложения с неправильным количеством аргументов
     */
    @Test
    public void testInvalidArgs() {
        // given:

        // when:
        Main.main(new String[]{"input1.txt", "output.txt", "errput.txt"});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8))
                .isEqualTo("Правильная передача аргументов: <inPath> <outPath>");
    }

    /**
     * Тест запуска приложения с неправильным количеством аргументов
     *
     * @throws IOException ошибка теста
     */
    @Test
    public void testSortHashMap() throws IOException {
        // given:
        String inPath = "./src/test/resources/testSort.txt";
        String outPath = "./src/test/resources/output_testSort.txt";
        String[] answer = {"C: 1,", "b: 2,", "A: 3,"};

        // when:
        Main.main(new String[]{inPath, outPath});

        // then:
        try (BufferedReader reader = new BufferedReader(new FileReader(outPath))) {
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                assertThat(line).isEqualTo(answer[i]);
            }
        }
        Path path = Paths.get(outPath);
        Files.deleteIfExists(path);
    }
}
