package net.jarlehansen.proto2javame.io.exception;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ProtoFileException extends Proto2JavaMeException {
	private static final long serialVersionUID = 7040685528096171294L;

	public ProtoFileException(final String message) {
		super(message);
	}
	
	public ProtoFileException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
