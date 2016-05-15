package annotation;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CAnnotationTest {
    @Test
    public void should_invoke_before_method() throws Exception {
//        IMonitor monitorOne = mock(MonitorOne.class);
        ProxyTarget target = ProxyFactory.proxy(ProxyTarget.class);
        target.doSomething();
//        verify(monitorOne, times(1)).before();
    }

}
