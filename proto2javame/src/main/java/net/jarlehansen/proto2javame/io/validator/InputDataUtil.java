package net.jarlehansen.proto2javame.io.validator;

import net.jarlehansen.proto2javame.io.exception.InvalidInputException;

import java.io.File;
import java.io.IOException;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public enum InputDataUtil {
    ;

    public static String getDestinationDirectory(final String... inputValues) {
        final String destinationDirectory;

        if (inputValues.length == 1) {
            destinationDirectory = getCurrentDirectory();
        } else {
            if (inputValues[0].length() > InputParams.JAVA_OUT_TAG.length()) {
                destinationDirectory = inputValues[0].substring(InputParams.JAVA_OUT_TAG.length());
            } else {
                throw new InvalidInputException("The destination directory was invalid, remember to include " + InputParams.JAVA_OUT_TAG);
            }
        }

        InputDataUtil.isValidDirectory(destinationDirectory);

        return destinationDirectory;
    }

    private static String getCurrentDirectory() {
        try {
            final File currentDir = new File(".");
            return currentDir.getCanonicalPath();
        }
        catch (IOException io) {
            throw new InvalidInputException(io.getClass().getName() + " when trying to find the current directory", io);
        }
    }

    public static String getProtoLocation(final String... inputValues) {
        final String protoLocation = inputValues[inputValues.length - 1];
        isValidFile(protoLocation);

        return protoLocation;
    }

    public static boolean isValidNumberOfParameters(final String... inputValues) {
        if (inputValues.length == InputParams.VALID_NUMBER_OF_PARAMS) {
            return true;
        } else {
            throw new InvalidInputException("The number of input parameters must be 2, it was: " + inputValues.length);
        }
    }

    private static void isValidDirectory(final String path) {
        final File dir = new File(path);
        if (!dir.isDirectory()) {
            throw new InvalidInputException(
                    "The --java_out directory was not specified or is not a valid directory, value: " + path);
        }
    }

    private static void isValidFile(final String path) {
        final File dir = new File(path);
        if (!dir.isFile()) {
            throw new InvalidInputException("The .proto file specified is not a valid file, value: " + path);
        }
    }

}
