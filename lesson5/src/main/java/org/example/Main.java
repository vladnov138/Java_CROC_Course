package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * Стартовый класс.
 */
public class Main {

    // Объявляем ExecutorService с максимально возможным числом потоков.
    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Точка старта приложения
     *
     * @param args стартовые аргументы
     */
    public static void main(String[] args) {

        var matrix = MatrixExample.E;

        printResult("detMultiThread", matrix, () -> {
            try {
                return detMultiThread(matrix.getMatrix());
            } catch (ExecutionException | InterruptedException e) {
                System.out.println("Поток был прерван или произошла ошибка при вычислении.");
                throw new RuntimeException(e);
            } finally {
                executorService.shutdown();
            }
        });
        printResult("detOneThread", matrix, () -> detOneThread(matrix.getMatrix()));
    }

    /**
     * Рекурсивный расчет определителя матрицы методом разложения по строке в один поток.
     *
     * @param a матрица
     * @return определитель матрицы
     */
    public static long detOneThread(long[][] a) {
        if (a.length == 1) {
            return a[0][0];
        }
        var result = 0L;
        for (var i = 0; i < a.length; i++) {
            var sign = (i % 2 == 0 ? 1 : -1);
            result = result + sign * a[i][0] * detOneThread(minor(a, i));
        }
        return result;
    }

    /**
     * Рекурсивный расчет определителя матрицы методом разложения по строке через многопоточность
     * (число потоков = a.length)
     *
     * @param a матрица
     * @return определитель матрицы
     * @throws ExecutionException   если вычисление было прервано или завершилось с ошибкой
     * @throws InterruptedException если текущий поток был прерван, пока ожидал завершения вычисления
     */
    public static long detMultiThread(long[][] a) throws ExecutionException, InterruptedException {
        if (a.length == 1) {
            return 1;
        }
        Future<Long>[] futures = new Future[a.length];
        for (var i = 0; i < a.length; i++) {
            var sign = (i % 2 == 0 ? 1 : -1);
            final int finalI = i;
            // Вот тут если вставить detMultiThread, то будет ооочень долго считать.
            // Предполагаю, что связано это с тем, что он насоздавал потоки больше возможного кол-ва, и те, видимо,
            // ждут своей очереди.
            futures[i] = executorService.submit(() -> sign * a[finalI][0] * detOneThread(minor(a, finalI)));
        }
        var result = new AtomicLong(0L); // для безопасности
        // Нам необходимо все записать в массив или лист с типом Future<Long>. Иначе будет блокироваться поток в
        // ожидании результата.
        for (Future<Long> future :
                futures) {
            result.addAndGet(future.get());
        }
        return result.get();
    }

    /**
     * Вычисляет минорную матрицу от заданной. Удаляется первый столбец и заданная строка.
     *
     * @param original  матрица, от которой требуется вычислить минорную
     * @param exceptRow удаляемая строка
     * @return минорная матрица
     */
    public static long[][] minor(final long[][] original, int exceptRow) {
        long[][] minor = new long[original.length - 1][original.length - 1];
        var minorLength = minor.length;
        for (int i = 0; i < exceptRow; i++) {
            System.arraycopy(original[i], 1, minor[i], 0, minorLength);
        }
        for (int i = exceptRow + 1; i < original.length; i++) {
            System.arraycopy(original[i], 1, minor[i - 1], 0, minorLength);
        }
        return minor;
    }

    /**
     * Выводит в консоль результат работы.
     *
     * @param method   название метода расчета
     * @param matrix   матрица из предложенных для примера
     * @param executor алгоритм расчета определителя матрицы
     */
    private static void printResult(String method, MatrixExample matrix, Supplier<Long> executor) {
        var start = System.currentTimeMillis();
        var det = executor.get();
        var executionTime = (System.currentTimeMillis() - start);
        System.out.println("Method -> " + method);
        System.out.println("Matrix name -> " + matrix.name());
        System.out.println("Matrix dimension -> " + matrix.getMatrix().length);
        System.out.println("Matrix determinant  = " + det + (det != matrix.getDeterminant() ? " ERROR!" : ""));
        System.out.println("Execution time -> " + executionTime);
        System.out.println();
    }
}