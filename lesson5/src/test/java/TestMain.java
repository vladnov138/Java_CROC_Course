import org.example.Main;
import org.example.MatrixExample;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тесты методов {@link Main#detOneThread(long[][])} и {@link Main#detMultiThread(long[][])}
 *
 * @author Vladislav Novikov
 */
public class TestMain {
    /**
     * Тест метода {@link Main#detOneThread(long[][])}
     * <p/>
     * Проверяет правильность подсчета определителя матрицы в одном потоке
     */
    @Test
    public void testDetOneThread() {
        // given:
        var matrixExample = MatrixExample.C;
        var matrix = matrixExample.getMatrix();
        long answer = matrixExample.getDeterminant();

        // when:
        long result = Main.detOneThread(matrix);

        // then:
        assertThat(result).isEqualTo(answer);
    }

    /**
     * Тест метода {@link Main#detMultiThread(long[][])}
     * <p/>
     * Проверяет правильность подсчета определителя матрицы в многопоточном режиме
     *
     * @throws ExecutionException   при ошибке теста
     * @throws InterruptedException при ошибке теста
     */
    @Test
    public void testDetMultiThread() throws ExecutionException, InterruptedException {
        // given:
        var matrixExample = MatrixExample.C;
        var matrix = matrixExample.getMatrix();
        long answer = matrixExample.getDeterminant();

        // when:
        long result = Main.detMultiThread(matrix);

        // then:
        assertThat(result).isEqualTo(answer);
    }
}
