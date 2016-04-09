package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public String findFirst(String pattern, String source) {
        String result = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        if (m.find()) {
            result = m.group(1);
        }
        return result;
    }
}
