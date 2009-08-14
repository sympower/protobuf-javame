package net.jarlehansen.proto2javame.io.protoinput.patterns.resource;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.MissingResourceException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 * Date: May 14, 2009
 */
public class ResourcePatternUtilTest {

    @Test
    public void testGetStringValidInput() {
        assertTrue(ResoucePatternUtil.FIELD_PARSER.getString("field.pattern").length() > 0);
    }

    @Test(expected = MissingResourceException.class)
    public void testGetStringInvalidInput() {
        ResoucePatternUtil.FIELD_PARSER.getString("not a valid pattern");
    }
}
