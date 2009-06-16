package net.jarlehansen.proto2javame.protobuf.integration;

import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javame.IntegrationTestObject;
import net.jarlehansen.proto2javame.protobuf.integration.tempfiles.javase.IntegrationTestObjectProto;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: Jun 15, 2009
 *         Time: 8:12:51 PM
 */
public class JavaSeIntegrationTestMatcher extends TypeSafeMatcher<IntegrationTestObject> {
    private final IntegrationTestObjectProto.IntegrationTestObject expected = IntegrationTestConstants.createIntegrationTestObjectJavaSe();

    public boolean matchesSafely(IntegrationTestObject actual) {
        return expected.getId() == actual.getId() &&
                expected.getLongNumber() == actual.getLongNumber() &&
                expected.getNameList().get(0).equals(actual.getName().get(0)) &&
                expected.getNameList().get(1).equals(actual.getName().get(1)) &&
                expected.getBytesObject().toStringUtf8().equals(actual.getBytesObject().toStringUtf8()) &&
                expected.getFloatObject() == actual.getFloatObject() &&
                expected.getAmount() == actual.getAmount();
    }

    public void describeTo(Description description) {
        description.appendText(expected.toString());
    }

    @Factory
    public static <T> Matcher<IntegrationTestObject> equalToJavaSeProtobufObject() {
        return new JavaSeIntegrationTestMatcher();
    }
}
