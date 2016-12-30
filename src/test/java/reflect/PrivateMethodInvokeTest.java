package reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PrivateMethodInvokeTest {
    @Test
    public void test_to_invoke_private_method_using_refection() throws Exception {
        PrivateMethodInvoke privateMethodInvoke = new PrivateMethodInvoke("one");
        Method innerMethod = privateMethodInvoke.getClass().getDeclaredMethod("innerMethod", null);
        innerMethod.setAccessible(true);
        String result = (String) innerMethod.invoke(privateMethodInvoke);
        assertThat(result, is("test"));
    }

    @Test
    public void should_able_to_change_private_field_with_reflection() throws Exception {
        PrivateMethodInvoke one = new PrivateMethodInvoke("One");
        assertThat(one.getName(), is("One"));
        Field name = PrivateMethodInvoke.class.getDeclaredField("name");
        name.setAccessible(true);
        name.set(one, "two");
        assertThat(one.getName(), is("two"));

    }
}
