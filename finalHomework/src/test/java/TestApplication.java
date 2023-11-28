import org.example.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApplication {
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
     * Тест запуска приложения со стандартными путями
     * @throws IOException при ошибке теста
     */
    @Test
    public void testDefaultStart() throws IOException {
        // given:
        String outDir = "./src/main/resources/";
        String expected = "Вывод выполнен в директорию: " + outDir +", файлы: topProducts.json, Meansales.json";

        // when:
        Main.main(new String[]{});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo(expected);
        Files.deleteIfExists(Paths.get(outDir + "topProducts.json"));
        Files.deleteIfExists(Paths.get(outDir + "Meansales.json"));
    }

    /**
     * Тест запуска приложения c неверным кол-вом аргументов
     */
    @Test
    public void testInvalidArgsCount() {
        // given:
        String expected = "Правильная передача аргументов: <products path> <salesmen path> <sales path> " +
                "<stockproducts path> <out directory>";

        // when:
        Main.main(new String[]{"1"});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo(expected);
    }

    /**
     * Тест запуска приложения с вручную введенным путем
     * @throws IOException при ошибке теста
     */
    @Test
    public void testValidArgs() throws IOException {
        // given:
        String productsPath = "./src/test/resources/products.json";
        String salesmenPath = "./src/test/resources/salesmen.json";
        String salesPath = "./src/test/resources/sales.json";
        String stockProductsPath = "./src/test/resources/stockProducts.json";
        String outDir = "./src/test/resources/";
        String expected = "Вывод выполнен в директорию: " + outDir + ", файлы: topProducts.json, Meansales.json";

        // when:
        Main.main(new String[]{productsPath, salesmenPath, salesPath, stockProductsPath, outDir});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo(expected);
        Files.deleteIfExists(Paths.get(outDir + "topProducts.json"));
        Files.deleteIfExists(Paths.get(outDir + "Meansales.json"));
    }

    /**
     * Тест запуска приложения с неправильным путем к файлам
     */
    @Test
    public void testInvalidPath() {
        // given:
        String productsPath = "./src/test/resources/products1.json";
        String salesmenPath = "./src/test/resources/salesmen.json";
        String salesPath = "./src/test/resources/sales.json";
        String stockProductsPath = "./src/test/resources/stockProducts.json";
        String outDir = "./src/test/resources/";
        String expected = "Возникла ошибка при чтении файлов. Убедитесь, что они существуют";

        // when:
        Main.main(new String[]{productsPath, salesmenPath, salesPath, stockProductsPath, outDir});

        // then:
        assertThat(outputStreamCaptor.toString(UTF_8)).isEqualTo(expected);
    }
}
