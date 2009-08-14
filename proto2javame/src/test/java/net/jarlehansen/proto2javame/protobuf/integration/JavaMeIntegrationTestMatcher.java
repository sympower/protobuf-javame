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
 *         Time: 8:47:34 PM
 */
class JavaMeIntegrationTestMatcher extends TypeSafeMatcher<IntegrationTestObjectProto.IntegrationTestObject> {
    private final IntegrationTestObject expected = IntegrationTestConstants.createIntegrationTestObjectJavaMe();

    public boolean matchesSafely(IntegrationTestObjectProto.IntegrationTestObject actual) {
        return expected.getId() == actual.getId() &&
                expected.getLongNumber() == actual.getLongNumber() &&
                expected.getName().get(0).equals(actual.getNameList().get(0)) &&
                expected.getName().get(1).equals(actual.getNameList().get(1)) &&
                expected.getBytesObject().toStringUtf8().equals(actual.getBytesObject().toStringUtf8()) &&
                expected.getFloatObject() == actual.getFloatObject() &&
                expected.getAmount() == actual.getAmount();
    }

    public void describeTo(Description description) {
        description.appendText(expected.toString());
    }

    @Factory
    public static <T> Matcher<IntegrationTestObjectProto.IntegrationTestObject> equalToJavaMeProtobufObject() {
        return new JavaMeIntegrationTestMatcher();
    }
}
