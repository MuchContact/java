package reflect;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PrivateMethodInvokeTest {
    @Test
    public void testInvokePrivateMethodUsingRefection() throws Exception {
        PrivateMethodInvoke privateMethodInvoke = new PrivateMethodInvoke();
        Method innerMethod = privateMethodInvoke.getClass().getDeclaredMethod("innerMethod", null);
        innerMethod.setAccessible(true);
        String result = (String) innerMethod.invoke(privateMethodInvoke);
        assertThat(result, is("test"));
    }
}
