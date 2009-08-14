package net.jarlehansen.proto2javame.business.sourcebuilder;

import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.factory.JavaSourceCodeBuilderFactory;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.privateandprotectedmethods.PrivateAndProtectedMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.resource.JavaSourceCodeUtil;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilder;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class JavaSourceCodeBuilderImpl implements JavaSourceCodeBuilder {
	private final InternalClassBuilder internalClassBuilder;
	private final MainClassBuilder mainClassBuilder;
	private final PrivateAndProtectedMethodsBuilder privateMethodsBuilder;
	private final PublicMethodsBuilder publicMethodsBuilder;
	private final StaticMethodsBuilder staticMethodsBuilder;

	private JavaSourceCodeBuilderImpl() {
		internalClassBuilder = JavaSourceCodeBuilderFactory.createInternalClassBuilder();
		mainClassBuilder = JavaSourceCodeBuilderFactory.createMainClassBuilder();
		privateMethodsBuilder = JavaSourceCodeBuilderFactory.createPrivateMethodsBuilder();
		publicMethodsBuilder = JavaSourceCodeBuilderFactory.createPublicMethodsBuilder();
		staticMethodsBuilder = JavaSourceCodeBuilderFactory.createStaticMethodsBuilder();
	}

	public static JavaSourceCodeBuilder newInstance() {
		return new JavaSourceCodeBuilderImpl();
	}

	public JavaFileOutput createJavaSourceCode(final ProtoFileInput protoInput) {
		final JavaFileOutput javaOutput = new JavaFileOutput(JavaSourceCodeUtil.createClassName(protoInput
				.getProtoClassName()), protoInput.getPackageName());

		javaOutput.setMainClass(mainClassBuilder.createMainClass(javaOutput.getClassName(), protoInput));
		javaOutput.setInternalClass(internalClassBuilder.createInternalClass(javaOutput.getClassName(), protoInput));
		javaOutput.setPrivateAndProtectedMethods(privateMethodsBuilder.createPrivateAndProtectedMethods(javaOutput.getClassName(), protoInput));
		javaOutput.setPublicMethods(publicMethodsBuilder.createPublicMethods(javaOutput.getClassName(), protoInput));
		javaOutput.setStaticMethods(staticMethodsBuilder.createStaticMethods(javaOutput.getClassName()));
		
		return javaOutput;
	}

}
