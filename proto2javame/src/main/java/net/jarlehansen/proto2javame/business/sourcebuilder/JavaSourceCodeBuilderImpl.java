package net.jarlehansen.proto2javame.business.sourcebuilder;

import com.google.inject.Inject;
import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.enums.EnumsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods.InstanceMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilder;
import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.domain.proto.ProtoFileInput;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public final class JavaSourceCodeBuilderImpl implements JavaSourceCodeBuilder {
    private final InternalClassBuilder internalClassBuilder;
    private final MainClassBuilder mainClassBuilder;
    private final EnumsBuilder enumsBuilder;
    private final InstanceMethodsBuilder privateMethodsBuilder;
    private final PublicMethodsBuilder publicMethodsBuilder;
    private final StaticMethodsBuilder staticMethodsBuilder;

    @Inject
    public JavaSourceCodeBuilderImpl(final InternalClassBuilder internalClassBuilder, final MainClassBuilder mainClassBuilder,
                                     final EnumsBuilder enumsBuilder, final InstanceMethodsBuilder privateMethodsBuilder,
                                     final PublicMethodsBuilder publicMethodsBuilder, final StaticMethodsBuilder staticMethodsBuilder) {
        this.internalClassBuilder = internalClassBuilder;
        this.mainClassBuilder = mainClassBuilder;
        this.enumsBuilder = enumsBuilder;
        this.privateMethodsBuilder = privateMethodsBuilder;
        this.publicMethodsBuilder = publicMethodsBuilder;
        this.staticMethodsBuilder = staticMethodsBuilder;
    }

    public List<JavaFileOutput> createJavaSourceCode(final List<ProtoFileInput> protoInputList) {
       final List<JavaFileOutput> javaOutputList = new ArrayList<JavaFileOutput>();

        for (ProtoFileInput protoInput : protoInputList) {
            final JavaFileOutput javaClass = new JavaFileOutput(protoInput.getPackageName(), protoInput.getProtoClassName());
            javaClass.setMainClass(mainClassBuilder.createMainClass(javaClass.getClassName(), protoInput));
            javaClass.setEnumClasses(enumsBuilder.createEnums(protoInput));
            javaClass.setInternalClass(internalClassBuilder.createInternalClass(javaClass.getClassName(), protoInput));
            javaClass.setPrivateAndProtectedMethods(privateMethodsBuilder.createPrivateAndProtectedMethods(javaClass.getClassName(), protoInput));
            javaClass.setPublicMethods(publicMethodsBuilder.createPublicMethods(javaClass.getClassName(), protoInput));
            javaClass.setStaticMethods(staticMethodsBuilder.createStaticMethods(javaClass.getClassName()));

            javaOutputList.add(javaClass);
        }

        return javaOutputList;
    }

}
