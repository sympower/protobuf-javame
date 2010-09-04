package net.jarlehansen.proto2javame.domain.proto;

import net.jarlehansen.proto2javame.io.exception.ProtoFileValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 1, 2010
 * Time: 9:42:08 PM
 */
public class EnumData {
    private int id;
	private ValidScopes scope;
	private String name;
    private final Map<Integer, String> enumValues = new HashMap<Integer, String>();

    public EnumData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ValidScopes getScope() {
        return scope;
    }

    public void setScope(ValidScopes scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEnumValue(final int id, final String value) {
        if(enumValues.containsKey(id)) {
            throw new ProtoFileValidationException("Enum field id must be unique, field: " + id + " - " + value);
        } else {
            enumValues.put(id, value);
        }
    }

    public Map<Integer, String> getEnumValues() {
        return enumValues;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EnumData");
        sb.append("{id=").append(id);
        sb.append(", scope=").append(scope);
        sb.append(", name='").append(name).append('\'');
        sb.append(", enumValues=").append(enumValues);
        sb.append('}');
        return sb.toString();
    }
}
