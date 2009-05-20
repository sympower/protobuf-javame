package net.jarlehansen.proto2javame.business.sourcebuilder.privatemethods;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.CommonResourceConstants;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class PrivateMethodsBuilderImpl implements PrivateMethodsBuilder {
    private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.PRIVATE_METHODS;

    private PrivateMethodsBuilderImpl() {
    }

    public static PrivateMethodsBuilder newInstance() {
        return new PrivateMethodsBuilderImpl();
    }

    public StringBuilder createPrivateMethods(final String className, final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(createByteArray(protoInput));
        builder.append(createWriteFields(protoInput));
        builder.append(createParseFields(className, protoInput));

        return builder;
    }

    private String createByteArray(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_CREATEBYTEARRAY_START));

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                builder.append(resourceFormat.getString(
                        PrivateMethodsConstants.KEY_PRIVATE_CREATEBYTEARRAY_FIELDS_LIST, ResourceFormatUtil.COMMON
                                .getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getListImpl().getImplementation()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), ResourceFormatUtil.COMMON
                                .getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getType()
                                .getDataTypeConstant(), field.getName()));
            } else if (field.getScope() == ValidScopes.REQUIRED) {
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_CREATEBYTEARRAY_FIELDS,
                        ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getJavaType()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), field.getName()));
            } else { // Must be optional
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_CREATEBYTEARRAY_FIELDS_OPTIONAL,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                        ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getJavaType()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), field.getName()));
            }
        }

        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_CREATEBYTEARRAY_END));

        return builder.toString();
    }

    private String createWriteFields(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_WRITEFIELDS_START,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_OUTPUT_WRITER)));

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_WRITEFIELDS_FIELDS_LIST,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getListImpl().getImplementation()),
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), ResourceFormatUtil.COMMON
                                .getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getType()
                                .getDataTypeConstant(), field.getName()));
            } else if (field.getScope() == ValidScopes.REQUIRED) {
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_WRITEFIELDS_FIELDS,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getJavaType()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), field.getName()));
            } else { // Must be optional
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_WRITEFIELDS_FIELDS_OPTIONAL,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getJavaType()), field.getName()));
            }
        }

        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_METHOD_END));
        return builder.toString();
    }

    private String createParseFields(final String className, final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_STATIC_PARSEFIELDS_START,
                className, ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_INPUT_READER)));

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                builder.append(resourceFormat.getString(
                        PrivateMethodsConstants.KEY_PRIVATE_STATIC_PARSEFIELDS_FIELDS_LIST, JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getType().getJavaType())));
            } else {
                builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_STATIC_PARSEFIELDS_FIELDS,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getName()), JavaSourceCodeUtil
                                .createCapitalLetterMethod(field.getType().getJavaType())));
            }
        }

        builder.append(resourceFormat.getString(PrivateMethodsConstants.KEY_PRIVATE_STATIC_PARSEFIELDS_END));
        return builder.toString();
    }
}
