package net.jarlehansen.proto2javame.io.exception;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class Proto2JavaMeException extends RuntimeException {
	private static final long serialVersionUID = 1993647030982430429L;

	Proto2JavaMeException(final String message) {
		super(message);
	}
	
	Proto2JavaMeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
