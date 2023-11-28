import org.example.Exceptions.ModelNotFoundException;
import org.example.Models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Тесты класса-модели {@link Product}
 *
 * @author Vladislav Novikov
 */
public class TestProduct {

    /**
     * Тест метода {@link Product#getProductById(Product[], int)}
     * <p/>
     * Проверяет правильность поиска товара по идентификатору
     * @throws ModelNotFoundException при ошибке теста
     */
    @Test
    public void testGetProductById() throws ModelNotFoundException {
        // given:
        Product[] products = {new Product(0, "Prod1"), new Product(1, "Prod2"),
                new Product(2, "Prod3")};
        int id = 1;
        Product expected = products[1];

        // when:
        Product result = Product.getProductById(products, id);

        // then:
        Assertions.assertEquals(expected, result);
    }

    /**
     * Тест метода {@link Product#getProductById(Product[], int)}
     * Проверяет ошибку при поиске товара по несуществующему идентификатору
     */
    @Test
    public void testGetInvalidProductById() {
        // given:
        Product[] products = {new Product(0, "Prod1"), new Product(1, "Prod2"),
                new Product(2, "Prod3")};
        int id = 3;

        // when:

        // then:
        assertThatThrownBy(() -> Product.getProductById(products, id)).isInstanceOf(ModelNotFoundException.class);
    }
}
