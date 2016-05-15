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

    @Before
    public void before() throws Exception {
        System.setOut(new PrintStream(output));
    }

    @After
    public void after() throws Exception {
        System.setOut(null);
    }

    @Test
    public void should_invoke_before_method() throws Exception {
//        IMonitor monitorOne = mock(MonitorOne.class);
        ProxyTarget target = ProxyFactory.proxy(ProxyTarget.class);
        target.doSomething();
        String result = output.toString();
        assertThat(result.contains("MonitorOne report: start to do something"), is(true));
        assertThat(result.contains("MonitorOne report: done for doing something"), is(true));
//        verify(monitorOne, times(1)).before();
    }

}
