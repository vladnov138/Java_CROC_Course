package croc.education.ws2023spb.knightsmove.chessposition;

import croc.education.ws2023spb.knightsmove.exceptions.IllegalPositionException;

/**
 * Расположение фигуры на традиционной шахматной доске 8x8.
 *
 * @author Vladislav Novikov
 */
public class ChessPositionImpl implements ChessPosition {

    public ChessPositionImpl(int x, int y) throws IllegalPositionException {
        if (x < 0 || x > 7 || y < 0 || y > 7) { // шахматная доска 8х8
            throw new IllegalPositionException("(" + x + " " + y + ")");
        }
        this.x = x;
        this.y = y;
        position = (char) ('a' + x) + String.valueOf(y + 1); // символ - это число, т.е. код символа. Прибавляем
                                                                // разницу и получим нужный символ ('a' + 1 = 'b')
                                                                // К y прибавляем 1, т.к. в изначальном виде отсчет
                                                                // идет с 0
    }

    int x;
    int y;
    String position;

    /**
     * Возвращает позицию фигуры по горизонтали.
     * <p/>
     * Возможные значения: 0 - 7.
     *
     * @return позицию фигуры по горизонтали
     */
    @Override
    public int x() {
        return x;
    }

    /**
     * Возвращает позицию фигуры по вертикали.
     * <p/>
     * Возможные значения: 0 - 7.
     *
     * @return позицию фигуры по вертикали
     */
    @Override
    public int y() {
        return y;
    }

    /**
     * Возвращает наименование клетки шахматной доски, на которой находится фигура, в
     * <a href="https://w.wiki/7pFN">шахматной нотации</a>.
     *
     * @return наименование клетки шахматной доски, на которой находится фигура, в шахматной нотации
     */
    @Override
    public String toString() {
        return position;
    }
}
