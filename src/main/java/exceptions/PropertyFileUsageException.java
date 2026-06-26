package exceptions;

public class PropertyFileUsageException extends FrameworkException {

    public PropertyFileUsageException(String message) {
        super(message); // calls parent constructor (FrameworkEXception constructor)
    }

    public PropertyFileUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
