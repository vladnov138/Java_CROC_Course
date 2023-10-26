package croc.education.ws2023spb.knightsmove;

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
     * @param message
     * @param cause
     */
    public IllegalPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public IllegalPositionException(Throwable cause) {
        super(cause);
    }
}
