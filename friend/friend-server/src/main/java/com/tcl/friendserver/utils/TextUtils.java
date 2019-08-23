package com.tcl.friendserver.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");


    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }


    /**
     * 下划线转驼峰
     * */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     * */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    // This pattern comes from android.util.Patterns. It has been tweaked to handle a "1" before
    // parens, so numbers such as "1 (425) 222-2342" match.
    private static final Pattern PHONE_PATTERN
            = Pattern.compile(                                  // sdd = space, dot, or dash
            "(\\+[0-9]+[\\- \\.]*)?"                    // +<digits><sdd>*
                    + "(1?[ ]*\\([0-9]+\\)[\\- \\.]*)?"         // 1(<digits>)<sdd>*
                    + "([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])"); // <digit><digit|sdd>+<digit>

    /**
     * Returns true if the string is a phone number.
     */
    public static boolean isPhoneNumber(String number) {
        // TODO: replace this function with libphonenumber's isPossibleNumber (see
        // PhoneNumberUtil). One complication is that it requires the sender's region which
        // comes from the CurrentCountryIso. For now, let's just do this simple match.
        if (TextUtils.isEmpty(number)) {
            return false;
        }

        Matcher match = PHONE_PATTERN.matcher(number);
        return match.matches();
    }
}
