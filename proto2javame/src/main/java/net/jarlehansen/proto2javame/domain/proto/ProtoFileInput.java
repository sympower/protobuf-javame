package net.jarlehansen.proto2javame.domain.proto;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;

import java.util.*;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class ProtoFileInput {
    private String protoClassName;
    private String packageName = "";

    private final List<FieldData> variables = new ArrayList<FieldData>();

    private EnumData currentEnum = null;
    private final Map<String, EnumData> enums = new HashMap<String, EnumData>();
    
    //TODO for json option
    private boolean supportJsonOpt = false; 

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
    
    public boolean isSupportJsonOpt() {
		return supportJsonOpt;
	}

	public void setSupportJsonOpt(boolean supportJsonOpt) {
		this.supportJsonOpt = supportJsonOpt;
	}

    public void addFieldData(final FieldData messageData) {
        boolean duplicate = false;

        for(FieldData fieldData : variables) {
            if(fieldData.getId() == messageData.getId()) {
                duplicate = true;
                break;
            }
        }

        if(duplicate) { 
            throw new ProtoFileValidationException("The id numbers must be unique, duplicate id: " + messageData.getId());
        } else {
            variables.add(messageData);
        }
    }

    public List<FieldData> getFields() {
        return variables;
    }

    public void setEnumIfAbsent(final String enumName) {
        if(!enums.containsKey(enumName)) {
            EnumData enumData = new EnumData();
            enumData.setName(enumName);

            enums.put(enumName, enumData);
            currentEnum = enumData;
        }
    }

    public void setCurrentEnumValue(final int id, final String value) {
        currentEnum.addEnumValue(id, value);
        enums.put(currentEnum.getName(), currentEnum);
    }

    public EnumData getEnumData(final String enumName) {
        return enums.get(enumName);
    }

    public EnumData getCurrentEnum() {
        return currentEnum;
    }

    public Collection<EnumData> getEnums() {
        return enums.values();
    }
}
