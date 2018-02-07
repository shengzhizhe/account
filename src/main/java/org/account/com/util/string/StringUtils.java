package org.account.com.util.string;

public class StringUtils {
    public static String str(String st) {
        if (st == null || st.isEmpty())
            return "";
        try {
            String s = new String(st.getBytes("UTF-8"), "UTF-8");
            return new String(s.getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }
}
