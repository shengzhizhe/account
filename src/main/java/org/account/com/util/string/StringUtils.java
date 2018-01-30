package org.account.com.util.string;

public class StringUtils {
    public static String Str(String str) {
        try {
            return new String(str.getBytes(), "utf8");
        } catch (Exception e) {
            return "";
        }
    }
}
