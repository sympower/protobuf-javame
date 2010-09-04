package net.jarlehansen.proto2javame.business.sourcebuilder.builder;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;
import net.jarlehansen.proto2javame.domain.proto.FieldData;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public final class InternalClassBuilderImpl implements InternalClassBuilder {
	private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.BUILDER;

	public InternalClassBuilderImpl() {
	}

	public StringBuilder createInternalClass(final String className, final ProtoFileInput protoInput) {
		final StringBuilder builder = new StringBuilder();
		builder.append(createClassInitialization());
		builder.append(createClassFields(protoInput));
		builder.append(createConstructor());
		builder.append(createMethods(protoInput));
		builder.append(createBuildMethods(className));
		builder.append(createEnd());

		return builder;
	}

	private String createClassInitialization() {
		return resourceFormat.getString(InternalClassConstants.KEY_BUILDER_INIT);
	}

	private String createClassFields(final ProtoFileInput protoInput) {
		final StringBuilder builder = new StringBuilder();

		for (FieldData field : protoInput.getFields()) {
			if (field.isList()) {
				builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_FIELDS_LIST, field
						.getListImpl().getImplementation(), field.getName()));
			} else {
				builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_FIELDS, field.getType()
						.getImplementationType(), field.getName()));
			}

			builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_FIELDS_BOOL, JavaSourceCodeUtil
					.createCapitalLetterMethod(field.getName())));
		}

		return builder.toString();
	}

	private String createConstructor() {
		return resourceFormat.getString(InternalClassConstants.KEY_BUILDER_CONSTRUCTOR);
	}

	private String createMethods(final ProtoFileInput protoInput) {
		final StringBuilder builder = new StringBuilder();

		for (FieldData field : protoInput.getFields()) {
			if (field.isList()) {
				builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_METHODS_LIST,
						JavaSourceCodeUtil.createCapitalLetterMethod(field.getName()), field.getListImpl()
								.getImplementation(), field.getName(), field.getType().getImplementationType()));

                if(field.getType().isPrimitiveType()) {
                    builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_METHODS_LIST_ADDELEMENT_PRIMITIVE, field.getName(), field.getType().getJavaObjectType()));
                } else {
                    builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_METHODS_LIST_ADDELEMENT_OBJECT, field.getName()));
                }
			} else {
				builder.append(resourceFormat.getString(InternalClassConstants.KEY_BUILDER_METHODS, JavaSourceCodeUtil
						.createCapitalLetterMethod(field.getName()), field.getType().getImplementationType(), field.getName()));
			}

		}

		return builder.toString();
	}

	private String createBuildMethods(final String className) {
		return resourceFormat.getString(InternalClassConstants.KEY_BUILDER_BUILD_METHOD, className);
	}

	private String createEnd() {
		return resourceFormat.getString(InternalClassConstants.KEY_BUILDER_END);
	}
}
