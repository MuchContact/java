package regex;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringTest {
    @Test
    public void should_handle_percent() throws Exception {
        String test = String.format("like \"%%%s%%\"", "test");
        assertThat(test, is("like \"%test%\""));
    }
}
