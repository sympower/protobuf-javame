package net.jarlehansen.proto2javame.business.generator.factory;

import net.jarlehansen.proto2javame.domain.metadata.InputMetaData;
import net.jarlehansen.proto2javame.io.validator.InputDataUtil;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public enum MetaDataFactory {
	;
	
	public static InputMetaData createInputMetaDataVo(final String[] inputValues) {
		InputDataUtil.isValidNumberOfParameters(inputValues);
        
	    return new InputMetaData(InputDataUtil.getDestinationDirectory(inputValues), InputDataUtil
					.getProtoLocation(inputValues));
	}
}
