package net.jarlehansen.proto2javame.io.exception;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class ProtoFileValidationException extends Proto2JavaMeException {
    private static final long serialVersionUID = -5060943626029064004L;

    private final String message;
    private int lineNumber = -1;

    public ProtoFileValidationException(final String message) {
        super(message);
        this.message = message;
    }

    public ProtoFileValidationException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public void setLineNumber(final int lineNumber) {
        this.lineNumber = lineNumber;
        
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getMessage() {
        final String msg;

        if(lineNumber > -1) {
            msg = this.message + ", line number: " + lineNumber;
        } else {
            msg = this.message;
        }

        return msg;
    }
}
