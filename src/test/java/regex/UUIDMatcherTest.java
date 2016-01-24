package regex;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UUIDMatcherTest {
    @Test
    public void testName() throws Exception {
        String source="f7ed4b22-ae98-4b4a-9da4-d973b5424f0d";
        assertThat(UUIDMatcher.isUUID(source), is(true));

    }
}
