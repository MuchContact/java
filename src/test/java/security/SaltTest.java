package security;

import org.junit.Test;

public class SaltTest {
    @Test
    public void testName() throws Exception {
        Salt salt = new Salt();
        salt.run();
    }
}
