package net.jarlehansen.proto2javame.business.sourcebuilder.staticmethods;

import net.jarlehansen.proto2javame.business.sourcebuilder.resource.ResourceFormatUtil;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public final class StaticMethodsBuilderImpl implements StaticMethodsBuilder {
	private final ResourceFormatUtil resourceFormat = ResourceFormatUtil.STATIC_METHODS;
	
	private StaticMethodsBuilderImpl() {
	}

	public static StaticMethodsBuilder newInstance() {
		return new StaticMethodsBuilderImpl();
	}

	public StringBuilder createStaticMethods(final String className) {
		final StringBuilder builder = new StringBuilder();
		builder.append(createParseFromByteArray(className));
		builder.append(createParseFromInputStream(className));
		builder.append(createClassEnd());

		return builder;
	}

	private String createParseFromByteArray(final String className) {
		return resourceFormat.getString(StaticMethodsConstants.KEY_STATIC_PARSEFROM_BYTEARRAY, className);
	}

	private String createParseFromInputStream(final String className) {
		return resourceFormat.getString(StaticMethodsConstants.KEY_STATIC_PARSEFROM_INPUTSTREAM, className);
	}
	
	private String createClassEnd() {
		return resourceFormat.getString(StaticMethodsConstants.KEY_CLASS_END);
	}
}
