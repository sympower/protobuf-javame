package net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.CommonResourceConstants;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class PublicMethodsBuilderImpl implements PublicMethodsBuilder {
    private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.PUBLIC_METHODS;

    private PublicMethodsBuilderImpl() {
    }

    public static PublicMethodsBuilder newInstance() {
        return new PublicMethodsBuilderImpl();
    }

    public StringBuilder createPublicMethods(final String className, final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(createGetMethods(protoInput));
        builder.append(createToString(protoInput));

        return builder;
    }

    private String createGetMethods(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                builder.append(resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_GETMETHODS, field
                        .getListImpl().getImplementation(),
                        JavaSourceCodeUtil.createCapitalLetterName(field.getName()), field.getName()));
            } else {
                builder.append(resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_GETMETHODS, field.getType()
                        .getJavaType(), JavaSourceCodeUtil.createCapitalLetterName(field.getName()), field.getName()));
            }

            if (field.getScope() == ValidScopes.OPTIONAL) {
                builder.append(resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_HASMETHODS,
                        JavaSourceCodeUtil.createCapitalLetterName(field.getName())));
            }
        }

        return builder.toString();
    }

    private String createToString(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_TOSTRING_START));

        for (FieldData field : protoInput.getFields()) {
            if (field.getScope() == ValidScopes.REQUIRED || field.getScope() == ValidScopes.REPEATED) {
                builder.append(resourceFormat
                        .getString(PublicMethodsConstants.KEY_PUBLIC_TOSTRING_FIELDS, field.getName()));
            } else { // Must be optional
                builder.append(resourceFormat
                        .getString(PublicMethodsConstants.KEY_PUBLIC_TOSTRING_FIELDS_OPTIONAL,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
            }
        }

        builder.append(resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_TOSTRING_END));

        return builder.toString();
    }

    private String createWriteToNewByteArray() {
        return resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_WRITETO_NEWBYTEARRAY);
    }

    private String createWriteToByteArray() {
        return resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_WRITETO_BYTEARRAY, ResourceFormatUtil.COMMON
                .getString(CommonResourceConstants.KEY_OUTPUT_WRITER), ResourceFormatUtil.COMMON
                .getString(CommonResourceConstants.KEY_INPUT_OUTPUT_FACTORY));
    }

    private String createWriteToOutputStream() {
        return resourceFormat.getString(PublicMethodsConstants.KEY_PUBLIC_WRITETO_OUTPUTSTREAM,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_OUTPUT_WRITER),
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_INPUT_OUTPUT_FACTORY));
    }

}
