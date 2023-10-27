package croc.education.ws2023spb.knightsmove.exceptions;

/**
 * Исключение, выбрасываемое в случае, если указана неправильная шахматная координата
 *
 * @author Vladislav Novikov
 */
public class IllegalPositionException extends Exception {
    /**
     * Стандартный конструктор.
     */
    public IllegalPositionException() {
        super();
    }

    /**
     * Конструктор с передачей сообщения.
     * @param message
     *          сообщение об ошибке
     */
    public IllegalPositionException(String message) {
        super(message);
    }

    /**
     * Конструктор с передачей сообщения и причины, вызвавшей это исключение.
     * @param message
     *          сообщение об ошибке
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public IllegalPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор с передачей причины, вызвавшей это исключение.
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public IllegalPositionException(Throwable cause) {
        super(cause);
    }
}
