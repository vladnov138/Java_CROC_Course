package croc.education.ws2023spb.knightsmove.chessposition;

import croc.education.ws2023spb.knightsmove.exceptions.IllegalPositionException;

/**
 * Класс, содержащий методы преобразования в объект расположения фигуры на шахматной доске из различных форматов.
 * 
 * @author Dmitry Malenok
 * @see ChessPosition
 */
public final class ChessPositionParser {

    /**
     * Конструктор.
     */
    private ChessPositionParser() {
        // Конструктор задан только для того, чтобы экземпляр класса случайно не создали.
    }

    /**
     * Разбирает наименование клетки шахматной доски, на которой находится фигура, в
     * <a href="https://w.wiki/7pFN">шахматной нотации</a> и возвращает соответствующий ей объект расположения фигуры на
     * шахматной доске.
     * 
     * @param position
     *            наименование клетки шахматной доски, на которой находится фигура
     * @return объект расположения фигуры на шахматной доске, соответствующий переданному наименованию клетки
     */
    public static ChessPosition parse(final String position) throws IllegalPositionException {
        if (position.length() > 2) {
            throw new IllegalPositionException();
        }
        char column = position.charAt(0); // вертикаль, т.е. буква
        char line = position.charAt(1); // горизонталь, т.е. цифра
        int x = -1;
        int y = -1;
        if (column >= 'a' && column <= 'h') {
            x = column - 'a'; // символ = код, т.е. число. Находим разницу кодов от начальной вертикали
        } else if (column >= 'A' && column <= 'H') { // Обеспечиваем регистронезависимость
            x = column - 'A';
        }
        y = line - '1';
        if (x == -1 || y < 0 || y > 7) { // Если значение х не изменилось или y вышел за пределы, то exception
            throw new IllegalPositionException(position);
        }
        return new ChessPositionImpl(x, y);
    }
}
