package net.jarlehansen.proto2javame.business.sourcebuilder.resource;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.MissingResourceException;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: May 14, 2009
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

    @Test
    public void testLineSeparatorWindows() {
        ResourceFormatUtil.BUILDER.lineSeparator = "\r\n";

        final String resourceString = ResourceFormatUtil.BUILDER.getString("internal.builder.init");

        final char[] chars = resourceString.toCharArray();

        final StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(Integer.toHexString(ch));
        }

        final String lastLineSeparator = sb.toString().toLowerCase().substring(sb.toString().length() - 2);

        // The Windows line separator is 0d 0a, but hexString will remove 0's. So the result should be 'da'
        assertEquals("da", lastLineSeparator);
    }

    @Test
    public void testLineSeparatorUnix() {
        ResourceFormatUtil.BUILDER.lineSeparator = "\n";

        final String resourceString = ResourceFormatUtil.BUILDER.getString("internal.builder.init");

        final char[] chars = resourceString.toCharArray();

        final StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(Integer.toHexString(ch));
        }

        final String lastLineSeparator = sb.toString().toLowerCase().substring(sb.toString().length() - 2);

        // The Unix line separator is 0a, but hexString will remove 0. So the result should be 'a'
        assertNotSame("d", lastLineSeparator.substring(0, 1));
        assertEquals("a", lastLineSeparator.substring(1));
    }

    @Test
    public void testLineSeparatorOSX() {
        ResourceFormatUtil.BUILDER.lineSeparator = "\r";

        final String resourceString = ResourceFormatUtil.BUILDER.getString("internal.builder.init");

        final char[] chars = resourceString.toCharArray();

        final StringBuilder sb = new StringBuilder();
        for(char ch : chars) {
            sb.append(Integer.toHexString(ch));
        }

        final String lastLineSeparator = sb.toString().toLowerCase().substring(sb.toString().length() - 1);

        // The Unix line separator is 0d, but hexString will remove 0. So the result should be 'd'
        assertEquals("d", lastLineSeparator);
    }
}
