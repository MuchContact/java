package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FinderTest {
    @Test
    public void should_get_id() throws Exception {
        final String url = "http://localhost/customers/eyu-12u-eb";
        final Pattern target = Pattern.compile("customers/([a-z0-9-]*)");
        final Matcher matcher = target.matcher(url);
        String id = null;
        if (matcher.find()) {
            id = matcher.group(1);
        }

        assertThat(id, is("eyu-12u-eb"));

    }
}
