package net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.CommonResourceConstants;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.*;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class InstanceMethodsBuilderImpl implements InstanceMethodsBuilder {
    private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.INSTANCE_METHODS;
    private ProtoFileInput protoInput;

    public InstanceMethodsBuilderImpl() {
    }

    public StringBuilder createPrivateAndProtectedMethods(final String className, final ProtoFileInput protoInput) {
        this.protoInput = protoInput;

        final StringBuilder builder = new StringBuilder();
        builder.append(createComputeSize());
        builder.append(createComputeSizeNestedMessages());
        builder.append(createWriteFields());
        builder.append(createParseFields(className));
        builder.append(createGetNextFieldNumber());
        builder.append(createPopulateWithField());

        return builder;
    }

    private String createComputeSize() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_START));

        for (FieldData field : protoInput.getFields()) {
            if (isValidType(field.getType())) {
                if (field.isList()) {
                    builder.append(resourceFormat.getString(
                            InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_FIELDS_LIST, ResourceFormatUtil.COMMON
                                    .getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL), JavaSourceCodeUtil
                                    .createCapitalLetterMethod(field.getListImpl().getImplementation()), JavaSourceCodeUtil
                                    .createCapitalLetterMethod(field.getName()), ResourceFormatUtil.COMMON
                                    .getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getType()
                                    .getDataTypeConstant(), field.getName()));
                } else if (field.getScope() == ValidScopes.REQUIRED) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_FIELDS,
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType()), JavaSourceCodeUtil
                                    .createCapitalLetterMethod(field.getName()), field.getName()));
                } else { // Must be optional
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_FIELDS_OPTIONAL,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
                }
            }
        }

        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_FIELDS_NESTED));
        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_COMPUTESIZE_END));

        return builder.toString();
    }

    private String createComputeSizeNestedMessages() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PRIVATE_COMPUTEMESSAGESIZE_START));

        for (FieldData field : protoInput.getFields()) {
            if (!isValidType(field.getType())) {
                if (field.getScope() == ValidScopes.REQUIRED) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PRIVATE_COMPUTEMESSAGESIZE_CUSTOMTYPE,
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
                } else if (field.isList()) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PRIVATE_COMPUTE_MESSAGESIZE_CUSTOMTYPE_LIST,
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getName()));
                } else { // Must be optional
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PRIVATE_COMPUTEMESSAGESIZE_CUSTOMTYPE_OPTIONAL,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_COMPUTE_SIZE_UTIL),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
                }
            }
        }

        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PRIVATE_COMPUTEMESSAGESIZE_END));

        return builder.toString();
    }

    private boolean isValidType(final DataType type) {
        boolean isValidType = false;

        for (ValidTypes tempType : ValidTypes.values()) {
            if (tempType.getImplementationType().equals(type.getImplementationType())) {
                isValidType = true;
                break;
            }
        }

        return isValidType;
    }

    private String createWriteFields() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_START,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_OUTPUT_WRITER)));

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                if (isValidType(field.getType())) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS_LIST,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getListImpl().getImplementation()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), ResourceFormatUtil.COMMON
                                    .getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getType()
                                    .getDataTypeConstant(), field.getName()));
                } else { // Must be a custom type
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS_LIST_NESTED,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_SUPPORTED_DATA_TYPES), field.getName()));
                }
            } else if (field.getScope() == ValidScopes.REQUIRED) {
                if (isValidType(field.getType())) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType()), JavaSourceCodeUtil
                                    .createCapitalLetterMethod(field.getName()), field.getName()));
                } else { // Must be a custom type
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS_NESTED,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
                }
            } else { // Must be optional
                if (isValidType(field.getType())) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS_OPTIONAL,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType()), field.getName()));
                } else { // Must be a custom type
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_FIELDS_OPTIONAL_NESTED,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getName()));
                }
            }
        }

        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PUBLIC_WRITEFIELDS_END));
        return builder.toString();
    }

    private String createParseFields(final String className) {
        return resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_PARSEFIELDS, className,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_INPUT_READER));
    }

    private String createGetNextFieldNumber() {
        return resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_GETNEXTFIELDNUMBER,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_INPUT_READER));
    }

    private String createPopulateWithField() {
        final StringBuilder builder = new StringBuilder();
        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_START,
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_INPUT_READER)));

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                if (isValidType(field.getType())) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_LIST,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType())));
                } else { // Must be a custom type
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_LIST_NESTED,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType())));
                }
            } else {
                if (isValidType(field.getType())) {
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType())));
                } else { // Must be a custom type
                    builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_NESTED,
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()),
                            JavaSourceCodeUtil.createCapitalLetterMethod(field.getType().getImplementationType())));
                }
            }
        }

        builder.append(resourceFormat.getString(InstanceMethodsConstants.KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_END));

        return builder.toString();
    }
}
