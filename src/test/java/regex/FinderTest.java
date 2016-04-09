package regex;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FinderTest {
    @Test
    public void should_get_id() throws Exception {
        final Finder finder = new Finder();
        String first = finder.findFirst("customers/([a-z0-9-]*)", "http://localhost/customers/eyu-12u-eb");
        assertThat(first, is("eyu-12u-eb"));

        first = finder.findFirst("customers/([a-z0-9-]*)", "http://localhost/customers/eyu-12u-eb/");
        assertThat(first, is("eyu-12u-eb"));
    }
}
