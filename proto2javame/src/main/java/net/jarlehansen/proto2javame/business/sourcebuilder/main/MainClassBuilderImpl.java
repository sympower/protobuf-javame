package net.jarlehansen.proto2javame.business.sourcebuilder.main;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.CommonResourceConstants;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;
import net.jarlehansen.proto2javame.domain.proto.ValidScopes;

import java.util.Date;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class MainClassBuilderImpl implements MainClassBuilder {
    private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.MAIN;

    private MainClassBuilderImpl() {
    }

    public static MainClassBuilder newInstance() {
        return new MainClassBuilderImpl();
    }

    public StringBuilder createMainClass(final String className, final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();
        builder.append(createPackage(protoInput));
        builder.append(createImports());
        builder.append(createClassInitialization(className));
        builder.append(createStaticUnknownTagHandlerInitialization());
        builder.append(createFields(protoInput));
        builder.append(createNewBuilderMethod());
        builder.append(createConstructor(className, protoInput));

        return builder;
    }

    private String createPackage(final ProtoFileInput protoInput) {
        final String packageName;

        if (protoInput.getPackageName() == null || "".equals(protoInput.getPackageName())) {
            packageName = "";
        } else {
            packageName = resourceFormat.getString(MainClassConstants.KEY_PACKAGE, protoInput.getPackageName());
        }

        return packageName;
    }

    private String createImports() {
        final String mainPackage = ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_MAIN);

        return resourceFormat.getString(MainClassConstants.KEY_IMPORTS, new Date().toString(), mainPackage, mainPackage +
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_FACTORY), mainPackage +
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_INPUT), mainPackage +
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_UTIL), mainPackage +
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_OUTPUT), mainPackage +
                ResourceFormatUtil.COMMON.getString(CommonResourceConstants.KEY_IMPORTS_PACKAGE_TAGHANDLER));
    }


    private String createClassInitialization(final String className) {
        return resourceFormat.getString(MainClassConstants.KEY_INIT, className);
    }

    private String createStaticUnknownTagHandlerInitialization() {
        return resourceFormat.getString(MainClassConstants.KEY_FIELDS_STATIC_UNKNOWNTAGHANDLER);
    }

    private String createFields(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();

        for (FieldData field : protoInput.getFields()) {
            if (field.isList()) {
                builder.append(resourceFormat.getString(MainClassConstants.KEY_FIELDS, field.getListImpl()
                        .getImplementation(), field.getName()));
            } else {
                builder.append(resourceFormat.getString(MainClassConstants.KEY_FIELDS, field.getType().getJavaType(),
                        field.getName()));
            }

            builder.append(resourceFormat.getString(MainClassConstants.KEY_FIELDS_NUMBER, JavaSourceCodeUtil
                    .createCapitalLetterName(field.getName()), Integer.toString(field.getId())));

            if (field.getScope() == ValidScopes.OPTIONAL) {
                builder.append(resourceFormat.getString(MainClassConstants.KEY_FIELDS_BOOL, JavaSourceCodeUtil
                        .createCapitalLetterMethod(field.getName())));
            }

            builder.append(resourceFormat.getString(MainClassConstants.KEY_FIELDS_END));
        }

        return builder.toString();
    }

    private String createNewBuilderMethod() {
        return resourceFormat.getString(MainClassConstants.KEY_NEW_BUILDER);
    }

    private String createConstructor(final String className, final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();

        builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR, className));
        builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_IF_START));

        boolean containsRequired = false;
        for (int i = 0; i < protoInput.getFields().size(); i++) {
            final FieldData field = protoInput.getFields().get(i);

            if (field.getScope() == ValidScopes.REQUIRED) {
                if (!containsRequired) {
                    containsRequired = true;
                }

                if (i != 0) {
                    builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_IF_AND));
                }

                builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_IF_FIELDS,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName())));
            }
        }

        // If no required fields are present, simply add if(true) so the program
        // always continues even if the fields are not set
        if (!containsRequired) {
            builder.append("true");
        }

        builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_IF_END));
        builder.append(createConstructorFields(protoInput));
        builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_ELSE));
        builder.append(createExceptionString(protoInput));
        builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_ELSE_END));
        builder.append(resourceFormat.getString(MainClassConstants.KEY_METHOD_END));

        return builder.toString();
    }

    private String createConstructorFields(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();

        for (FieldData field : protoInput.getFields()) {
            builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCOTR_FIELDS, field.getName()));

            if (field.getScope() == ValidScopes.OPTIONAL) {
                builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_FIELDS_BOOL,
                        JavaSourceCodeUtil.createCapitalLetterMethod(field.getName())));
            }
        }

        return builder.toString();
    }

    private String createExceptionString(final ProtoFileInput protoInput) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < protoInput.getFields().size(); i++) {
            final FieldData field = protoInput.getFields().get(i);

            if (field.getScope() == ValidScopes.REQUIRED) {
                builder.append(resourceFormat.getString(MainClassConstants.KEY_CONSTRUCTOR_ELSE_FIELDS,
                        field.getName(), JavaSourceCodeUtil.createCapitalLetterName(field.getName())));
            }
        }

        return builder.toString();
    }
}
