import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.Models.Product;
import org.example.Utils.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestJsonUtil {
    JsonUtil jsonUtil;
    Gson gson;

    @BeforeEach
    protected void setUp() {
        gson = new Gson();
        jsonUtil = new JsonUtil();
    }

    @Test
    public void testConvertMeanSales() {
        // given:
        HashMap<String, Double> meanSales = new HashMap<>();
        meanSales.put("24.11.2023", 2.0);
        meanSales.put("25.11.2023", 3.0);
        JsonObject expected = gson.fromJson("{\"24.11.2023\": 2.0, \"25.11.2023\": 3.0}",
                JsonObject.class);
        // when:
        JsonObject result = jsonUtil.convertMeanSales(meanSales);
        // then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testConvertProducts() {
        // given:
        Product[] products = {new Product(0, "Prod1"), new Product(1, "Prod2")};
        JsonObject expected = gson.fromJson(
                "{\"top\": [{\"id\":0,\"name\":\"Prod1\"},{\"id\":1,\"name\":\"Prod2\"}]}",
                JsonObject.class);
        // when:
        JsonObject result = jsonUtil.convertTopProducts(products);
        // then:
        Assertions.assertEquals(expected, result);
    }
}
