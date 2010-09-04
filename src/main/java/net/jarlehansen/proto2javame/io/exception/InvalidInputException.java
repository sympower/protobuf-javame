package net.jarlehansen.proto2javame.io.exception;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class InvalidInputException extends Proto2JavaMeException {
	private static final long serialVersionUID = -6176893558158162823L;

	public InvalidInputException(final String message) {
		super(message);
	}
	
	public InvalidInputException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
