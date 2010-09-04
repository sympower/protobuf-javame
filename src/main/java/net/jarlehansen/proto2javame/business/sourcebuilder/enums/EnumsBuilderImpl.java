package net.jarlehansen.proto2javame.business.sourcebuilder.enums;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.EnumData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

import java.util.Collection;
import java.util.Map;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 2, 2010
 * Time: 1:37:34 PM
 */
public class EnumsBuilderImpl implements EnumsBuilder {

    public EnumsBuilderImpl() {
    }

    public StringBuilder createEnums(final ProtoFileInput protoInput) {
        Collection<EnumData> enums = protoInput.getEnums();

        final StringBuilder builder = new StringBuilder();
        
        for(EnumData enumData : enums) {
            builder.append(createInnerMainClass(enumData.getName()));
            builder.append(createInnerFields(enumData.getEnumValues()));
            builder.append(createInnerClassMethod(enumData.getEnumValues()));
        }

        return builder;
    }

    private String createInnerMainClass(final String enumName) {
        return ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_INIT, enumName);
    }

    private String createInnerFields(final Map<Integer, String> enumValues) {
        final StringBuilder builder = new StringBuilder();

        for(Map.Entry<Integer, String> entry : enumValues.entrySet()) {
            builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_FIELDS, entry.getValue(), entry.getKey().toString()));
        }

        return builder.toString();
    }
    

    private String createInnerClassMethod(final Map<Integer, String> enumValues) {
        final StringBuilder builder = new StringBuilder();
        builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_METHOD_START));
        builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_METHOD_SWITCH_START));

        for(Map.Entry<Integer, String> entry : enumValues.entrySet()) {
            builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_METHOD_SWITCH_CASE, entry.getKey().toString(), entry.getValue()));
        }

        builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_METHOD_SWITCH_END));
        builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_METHOD_END));
        builder.append(ResourceFormatUtil.ENUM.getString(EnumsConstants.KEY_END));

        return builder.toString();
    }

    
}
