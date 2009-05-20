package net.jarlehansen.proto2javame.business.sourcebuilder.factory;

import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.builder.InternalClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.main.MainClassBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.privatemethods.PrivateMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.privatemethods.PrivateMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.publicmethods.PublicMethodsBuilderImpl;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilder;
import net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods.StaticMethodsBuilderImpl;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 * 
 */
public enum JavaSourceCodeBuilderFactory {
	;
	
	public static InternalClassBuilder createInternalClassBuilder() {
		return InternalClassBuilderImpl.newInstance();
	}
	
	public static MainClassBuilder createMainClassBuilder() {
		return MainClassBuilderImpl.newInstance();
	}
	
	public static PrivateMethodsBuilder createPrivateMethodsBuilder() {
		return PrivateMethodsBuilderImpl.newInstance();
	}
	
	public static PublicMethodsBuilder createPublicMethodsBuilder() {
		return PublicMethodsBuilderImpl.newInstance();
	}
	
	public static StaticMethodsBuilder createStaticMethodsBuilder() {
		return StaticMethodsBuilderImpl.newInstance();
	}
}
