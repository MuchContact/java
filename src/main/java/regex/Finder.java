package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(\\d\\d)(\\d)");
        Matcher m = p.matcher("a123b");
        while(m.find()){
            System.out.println(m.group());
        }
        System.out.println(m.matches());
    }
}
