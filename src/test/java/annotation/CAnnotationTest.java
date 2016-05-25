package annotation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CAnnotationTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    final static PrintStream origin = System.out;

    @Before
    public void before() throws Exception {
        System.setOut(new PrintStream(output));
    }

    @After
    public void after() throws Exception {
        System.setOut(origin);
    }

    @Test
    public void should_invoke_before_and_after_methods() throws Exception {
//        IMonitor monitorOne = mock(MonitorOne.class);
        ProxyTarget target = ProxyFactory.proxy(ProxyTarget.class);
        target.doSomething();
        String result = output.toString();
        assertThat(result.contains("MonitorOne report: start to do something"), is(true));
        assertThat(result.contains("MonitorOne report: done for doing something"), is(true));
        System.setOut(origin);
        System.out.println(output.toString());
//        verify(monitorOne, times(1)).before();
    }

}
