package croc.education.ws2023spb.knightsmove.knithsmovechecker;

import croc.education.ws2023spb.knightsmove.exceptions.IllegalMoveException;
import croc.education.ws2023spb.knightsmove.exceptions.IllegalPositionException;

/**
 * Обработчик, проверяющий, что последовательность клеток на шахматной доске может быть пройдена ходом коня.
 * 
 * @author Dmitry Malenok
 */
public interface KnightsMoveChecker {

    /**
     * Проверяет, что указанная последовательность клеток на шахматной доске может быть пройдена ходом шахматного коня.
     * 
     * @param positions
     *            последовательность клеток на шахматной доске, которую надо обойти от предыдущей клетки к следующей
     * @throws IllegalMoveException
     *             если при перемещении шахматного коня из текущей клетки в следующую происходит с нарушением правил
     * @throws IllegalPositionException
     *              если позиция фигуры в традиционном шахматном поле 8х8 указана некорректно
     */
    void check(String[] positions) throws IllegalMoveException, IllegalPositionException;
}
