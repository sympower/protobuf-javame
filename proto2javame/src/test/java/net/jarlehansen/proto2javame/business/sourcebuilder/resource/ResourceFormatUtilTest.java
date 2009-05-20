package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.MissingResourceException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 * Date: May 14, 2009
 */
public class ResourceFormatUtilTest {

    @Test
    public void testGetStringValidInput() {
        assertTrue(ResourceFormatUtil.BUILDER.getString("internal.builder.init").length() > 0);
    }

    @Test(expected = MissingResourceException.class)
    public void tesGetStringInvalidInput() {
        ResourceFormatUtil.BUILDER.getString("not a valid key");
    }

    @Test
    public void testGetStringVarArgsValidInput() {
        assertTrue(ResourceFormatUtil.BUILDER.getString("internal.builder.fields", "field1", "field2").length() > 0);
    }

    @Test(expected = MissingResourceException.class)
    public void testGetStringVarArgsInvalidInput() {
        ResourceFormatUtil.BUILDER.getString("not a valid key");
    }
}
