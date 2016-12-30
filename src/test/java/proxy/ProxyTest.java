package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProxyTest {
    @Test
    public void should_proxy_customer_interface() throws Exception {
        MyInterface real = mock(MyInterface.class);
        when(real.getName()).thenReturn("MockObject");
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(real);
        MyInterface proxy =
                (MyInterface) Proxy.newProxyInstance(
                        MyInterface.class.getClassLoader(),
                        new Class[]{MyInterface.class},
                        proxyHandler);
        Monitor mockMonitor = mock(Monitor.class);
        proxyHandler.setMonitor(mockMonitor);
        String name = proxy.getName();
        assertThat(name, is("MockObject"));
        verify(mockMonitor).doSomething();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_able_to_proxy_customer_class() throws Exception {
        MyClass real = new MyClass();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(real);
        Proxy.newProxyInstance(
                        MyClass.class.getClassLoader(),
                        new Class[]{MyClass.class},
                        proxyHandler);
    }

    private interface MyInterface {
        String getName();
    }

    private static class DynamicProxyHandler implements InvocationHandler {
        private final MyInterface target;
        private Monitor monitor;

        public DynamicProxyHandler(MyInterface real) {
            this.target = real;
        }

        public void setMonitor(Monitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            monitor.doSomething();
            return method.invoke(target, args);
        }
    }

    private static class Monitor {
        public void doSomething() {

        }
    }

    private static class MyClass implements MyInterface {
        @Override
        public String getName() {
            return null;
        }
    }
}
