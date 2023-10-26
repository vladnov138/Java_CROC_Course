package croc.education.ws2023spb.knightsmove;

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
     * @param message
     * @param cause
     */
    public IllegalMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public IllegalMoveException(Throwable cause) {
        super(cause);
    }
}
