package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UUIDMatcher {
    public static boolean isUUID(String source) {
        Pattern pattern = Pattern.compile("(\\w{8}(-\\w{4}){3}-\\w{12}?)");
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }
}
