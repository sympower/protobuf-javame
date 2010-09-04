package net.jarlehansen.proto2javame.domain.proto;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ValidScopes {
	ENUM_NONE(""),
    OPTIONAL("optional"),
	REQUIRED("required"),
	REPEATED("repeated");
	
	private final String scope;
	
	private ValidScopes(final String scope) {
		this.scope = scope;
	}
	
	public String getScopeValue() {
		return scope;
	}
}
