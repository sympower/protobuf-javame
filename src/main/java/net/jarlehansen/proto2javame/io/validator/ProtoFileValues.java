package net.jarlehansen.proto2javame.io.validator;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum ProtoFileValues {
	REQUIRED_TAG("required"),
	OPTIONAL_TAG("optional"),
	REPEATED_TAG("repeated"),
	END_FIELD_TAG(";");
	
	private final String tag;
	
	private ProtoFileValues(final String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
