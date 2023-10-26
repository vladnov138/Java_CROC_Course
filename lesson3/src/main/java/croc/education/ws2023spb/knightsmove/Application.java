package croc.education.ws2023spb.knightsmove;

/**
 * Приложение, проверяющее возможность прохождения последовательности клеток на шахматной доске ходом коня.
 */
public final class Application {

    /**
     * Основной метод приложения.
     *
     * @param args
     *            аргументы
     */
    public static void main(final String[] args) {
        KnightsMoveChecker knightsMoveChecker = KnightsMoveCheckerFactory.get();
        try {
            knightsMoveChecker.check(args);
            System.out.print("OK");
        } catch (IllegalMoveException e) {
            System.out.print("конь так не ходит: " + e.getMessage());
        } catch (IllegalPositionException e) {
            System.out.print("невалидные координаты: ");
        }
    }
}
