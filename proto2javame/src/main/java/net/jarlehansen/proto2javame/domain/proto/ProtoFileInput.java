package net.jarlehansen.proto2javame.domain.proto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class ProtoFileInput {
	private String protoClassName;
	private String packageName = "";
	
	private final List<FieldData> variables = new ArrayList<FieldData>();

	public ProtoFileInput() {
	}

	public void setPackageName(final String packageName) {
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}
	
	public void setProtoClassName(final String protoClassName) {
		this.protoClassName = protoClassName;
	}
	
	public String getProtoClassName() {
		return protoClassName;
	}
	
	public void addFieldData(final FieldData messageData) {
		variables.add(messageData);
	}
	
	public List<FieldData> getFields() {
		return variables;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ProtoFileInput");
        sb.append("{protoClassName='").append(protoClassName).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", variables=").append(variables);
        sb.append('}');
        return sb.toString();
    }
}
