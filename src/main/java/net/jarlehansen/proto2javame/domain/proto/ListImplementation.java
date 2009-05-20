package net.jarlehansen.proto2javame.domain.proto;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ListImplementation {
	VECTOR("java.util.Vector");
	
	private final String listImpl;
	
	private ListImplementation(final String listImpl) {
		this.listImpl = listImpl;
	}
	
	public String getImplementation() {
		return listImpl;
	}
}
