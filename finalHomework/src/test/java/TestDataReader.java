import org.example.Models.Product;
import org.example.Models.Sale;
import org.example.Models.Salesman;
import org.example.Models.StockProduct;
import org.example.Utils.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestDataReader {
    private DataReader dataReader;

    @BeforeEach
    protected void setUp() {
        dataReader = new DataReader();
    }

    @Test
    public void testReadProducts() throws IOException {
        // given:
        final String filename = "./src/test/resources/products.json";
        Product[] expected = {new Product(0, "Prod1"), new Product(1, "Prod2")};

        // when:
        Product[] result = dataReader.readProducts(filename);

        // then:
        Assertions.assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i].id(), result[i].id());
            Assertions.assertEquals(expected[i].name(), result[i].name());
        }
    }

    @Test
    public void testReadSalesmen() throws IOException {
        // given:
        final String filename = "./src/test/resources/salesmen.json";
        Salesman[] expected = {new Salesman(0, "Test", "Testerov"),
                new Salesman(1, "Test2", "Testerov2")};

        // when:
        Salesman[] result = dataReader.readSalesmen(filename);

        // then:
        Assertions.assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i].id(), result[i].id());
            Assertions.assertEquals(expected[i].firstName(), result[i].firstName());
            Assertions.assertEquals(expected[i].lastName(), result[i].lastName());
        }
    }

    @Test
    public void testReadSales() throws ParseException, IOException {
        // given:
        final String filename = "./src/test/resources/sales.json";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Sale[] expected = {new Sale(0, 0, 0, 1, dateFormat.parse("2023-11-17")),
                new Sale(1, 0, 1, 2, dateFormat.parse("2023-11-17"))};

        // when:
        Sale[] result = dataReader.readSales(filename);

        // then:
        Assertions.assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i].id(), result[i].id());
            Assertions.assertEquals(expected[i].salesmanId(), result[i].salesmanId());
            Assertions.assertEquals(expected[i].productId(), result[i].productId());
            Assertions.assertEquals(expected[i].salesCount(), result[i].salesCount());
            Assertions.assertEquals(expected[i].saleDate(), result[i].saleDate());
        }
    }

    @Test
    public void testReadStockProducts() throws IOException {
        // given:
        final String filename = "./src/test/resources/stockProduct.json";
        StockProduct[] expected = {new StockProduct(0, 0, 1.0, 1),
                new StockProduct(0, 1, 2.0, 2)};

        // when:
        StockProduct[] result = dataReader.readStockProducts(filename);

        // then:
        Assertions.assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i].salesmanId(), result[i].salesmanId());
            Assertions.assertEquals(expected[i].productId(), result[i].productId());
            Assertions.assertEquals(expected[i].cost(), result[i].cost());
            Assertions.assertEquals(expected[i].productCount(), result[i].productCount());
        }
    }
}
