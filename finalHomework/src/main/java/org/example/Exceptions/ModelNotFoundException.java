package org.example.Exceptions;

/**
 * Класс-исключение для случая, если модель не найдена
 *
 * @author Vladislav Novikov
 */
public class ModelNotFoundException extends Exception {
    /**
     * Стандартный конструктор.
     */
    public ModelNotFoundException() {
        super();
    }

    /**
     * Конструктор с передачей сообщения.
     * @param message
     *          сообщение об ошибке
     */
    public ModelNotFoundException(String message) {
        super(message);
    }

    /**
     * Конструктор с передачей сообщения и причины, вызвавшей это исключение.
     * @param message
     *          сообщение об ошибке
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор с передачей причины, вызвавшей это исключение.
     * @param cause
     *          исключение, которое послужило причиной вызова исключения
     */
    public ModelNotFoundException(Throwable cause) {
        super(cause);
    }
}
