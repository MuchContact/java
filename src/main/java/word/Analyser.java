package word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyser {
    protected boolean isChineseWord(char s) {
        int v = (int) s;
        return (v >= 19968 && v <= 171941);
    }

    public List<String> splitByWord(String s) {
        List<String> words = new ArrayList<>();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder(10);
        boolean stop;
        for (char c : chars) {
            stop = isStopped(c);
            if (stop) {
                if (sb.length() > 0)
                    words.add(sb.toString());
                sb.delete(0, sb.length());
                if (c != ' ')
                    words.add(c + "");
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0)
            words.add(sb.toString());
        return words;
    }

    private boolean isStopped(char c) {
        String tmp = c + "";
        Pattern compile = Pattern.compile("[^a-zA-Z'-_]");
        Matcher matcher = compile.matcher(tmp);
        return matcher.matches();
    }
}
