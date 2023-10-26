package croc.education.ws2023spb.knightsmove;

public class KnightsMoveCheckerImpl implements KnightsMoveChecker {
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
    @Override
    public void check(String[] positions) throws IllegalMoveException, IllegalPositionException {
        for (int i = 1; i < positions.length; i++) {
            ChessPosition startPosition = ChessPositionParser.parse(positions[i - 1]);
            ChessPosition finishPosition = ChessPositionParser.parse(positions[i]);
            int[][] steps = {{-1, 2}, {1, 2}, {-1, -2}, {1, -2}}; // Возможные шаги коня. На деле их 8, но во второй
                                                                  // половине координаты меняются местами (-1,2 и 2,-1)
            int difX = startPosition.x() - finishPosition.x();
            int difY = startPosition.y() - finishPosition.y();
            boolean correctStep = false;
            for (int[] step :
                    steps) {
                if (difX == step[0] && difY == step[1] || difX == step[1] && difY == step[0]) {
                    correctStep = true;
                    break;
                }
            }
            if (!correctStep) {
                throw new IllegalMoveException(startPosition + " -> " + finishPosition);
            }
        }
    }
}
