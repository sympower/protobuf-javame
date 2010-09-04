package net.jarlehansen.proto2javame.modules.business;

import com.google.inject.AbstractModule;
import net.jarlehansen.proto2javame.business.generator.CodeGenerator;
import net.jarlehansen.proto2javame.business.generator.CodeGeneratorImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.JavaSourceCodeBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.enums.EnumsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.enums.EnumsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods.InstanceMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods.InstanceMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilderImpl;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 30, 2010
 * Time: 6:18:29 PM
 */
public class CodeGeneratorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CodeGenerator.class).to(CodeGeneratorImpl.class);
        bind(JavaSourceCodeBuilder.class).to(JavaSourceCodeBuilderImpl.class);
        bind(InternalClassBuilder.class).to(InternalClassBuilderImpl.class);
        bind(MainClassBuilder.class).to(MainClassBuilderImpl.class);
        bind(InstanceMethodsBuilder.class).to(InstanceMethodsBuilderImpl.class);
        bind(PublicMethodsBuilder.class).to(PublicMethodsBuilderImpl.class);
        bind(StaticMethodsBuilder.class).to(StaticMethodsBuilderImpl.class);
        bind(EnumsBuilder.class).to(EnumsBuilderImpl.class);
    }
}
