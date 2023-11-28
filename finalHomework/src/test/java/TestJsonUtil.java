import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;
import org.example.Utils.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тесты класса {@link JsonUtil}
 *
 * @author Vladislav Novikov
 */
public class TestJsonUtil {
    JsonUtil jsonUtil;
    Gson gson;

    /**
     * Инициализация, запускаемая перед каждым тестом.
     */
    @BeforeEach
    protected void setUp() {
        gson = new Gson();
        jsonUtil = new JsonUtil();
    }

    /**
     * Тест метода {@link JsonUtil#convertMeanSales(double)}
     * </p>
     * Проверяет корректность конвертации данных о среднем кол-ве продаж
     */
    @Test
    public void testConvertMeanSales() {
        // given:
        double meanSales = 3.1;
        JsonObject expected = gson.fromJson("{\"mean_sales\": 3.1}",
                JsonObject.class);

        // when:
        JsonObject result = jsonUtil.convertMeanSales(meanSales);

        // then:
        Assertions.assertEquals(expected, result);
    }

    /**
     * Тест метода {@link JsonUtil#convertTopProducts(Map, Product[])}
     * </p>
     * Проверяет корректность конвертации информации о товарах.
     * @throws ModelNotFoundException при ошибке теста
     */
    @Test
    public void testConvertProducts() throws ModelNotFoundException {
        // given:
        Product[] products = {new Product(0, "Prod1"), new Product(1, "Prod2")};
        var topProducts = new HashMap<Integer, Integer>();
        topProducts.put(0, 10);
        JsonObject expected = gson.fromJson(
                "{\"top\": [{\"id\":0,\"name\":\"Prod1\", \"sales_count\": 10}]}",
                JsonObject.class);

        // when:
        JsonObject result = jsonUtil.convertTopProducts(topProducts, products);

        // then:
        Assertions.assertEquals(expected, result);
    }
}
