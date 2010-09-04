package net.jarlehansen.proto2javame.io.protoinput.util;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Sep 1, 2010
 * Time: 9:23:04 PM
 */
public enum LineSplitterUtil {
    ;

    public static String[] split(final String line) {
        final String[] tempStrings = line.split("[\\s]++");
        
        final List<String> stringsList = new ArrayList<String>();
        for(String string : tempStrings) {
            if(string != null) {
                String tempString = string.trim();

                if(tempString.length() > 0) {
                    stringsList.add(tempString);
                }
            }
        }
        
        return stringsList.toArray(new String[]{});
    }
}
