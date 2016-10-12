package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProxyTest {
    @Test
    public void should_proxy_subject_interface() throws Exception {
        Subject real = mock(Subject.class);
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(real);
        Subject proxy = (Subject) Proxy.newProxyInstance(Subject.class
                        .getClassLoader(), new Class[]{Subject.class},
                proxyHandler);
        Decorator mockDecorator = mock(Decorator.class);
        proxyHandler.setDecorator(mockDecorator);
        proxy.getName();
        verify(mockDecorator).doSomething();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_able_to_proxy_subject_class() throws Exception {
        SubjectClass real = new SubjectClass();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(real);
        SubjectClass proxy = (SubjectClass) Proxy.newProxyInstance(SubjectClass.class
                        .getClassLoader(), new Class[]{SubjectClass.class},
                proxyHandler);
        Decorator mockDecorator = mock(Decorator.class);
        proxyHandler.setDecorator(mockDecorator);
        proxy.getName();
        verify(mockDecorator).doSomething();
    }

    private interface Subject {
        String getName();
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private final Subject target;
        private Decorator decorator;

        public DynamicProxyHandler(Subject real) {
            this.target = real;
        }

        public void setDecorator(Decorator decorator) {
            this.decorator = decorator;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            decorator.doSomething();
            return method.invoke(target, args);
        }
    }

    private class Decorator {
        public void doSomething() {

        }
    }

    private class SubjectClass implements Subject {
        @Override
        public String getName() {
            return null;
        }
    }
}
