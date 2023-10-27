package croc.education.ws2023spb.knightsmove.exceptions;

/**
 * Исключение, выбрасываемое в случае, если при перемещении шахматного коня из текущей клетки в следующую происходит с
 * нарушением правил.
 * 
 * @author Dmitry Malenok
 */
public class IllegalMoveException extends Exception {
    /**
     * Стандартный конструктор.
     */
    public IllegalMoveException() {
        super();
    }

    /**
     * Конструктор с передачей сообщения.
     * @param message
     *          сообщение об ошибке
     */
    public IllegalMoveException(String message) {
        super(message);
    }

    /**
     * Конструктор с передачей сообщения и причины, вызвавшей это исключение.
     * @param message
     *          сообщение об ошибке
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public IllegalMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор с передачей причины, вызвавшей это исключение.
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public IllegalMoveException(Throwable cause) {
        super(cause);
    }
}
