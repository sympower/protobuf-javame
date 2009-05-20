package net.jarlehansen.proto2javame.io.exception;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ProtoFileValidationException extends Proto2JavaMeException {
	private static final long serialVersionUID = -5060943626029064004L;
	
	private int lineNumber = -1;
	
	public ProtoFileValidationException(final String message) {
		super(message);
	}
	
	public ProtoFileValidationException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public void setLineNumber(final int lineNumber) {
		this.lineNumber = lineNumber;
	}

    public int getLineNumber() {
        return lineNumber;
    }
	
	public String toString() {
		final StringBuilder tempMessage = new StringBuilder();
		tempMessage.append(super.toString());
		
		if(lineNumber > -1) {
			tempMessage.append(", line number: ").append(lineNumber);
		}
		
		return tempMessage.toString();
	}
}
