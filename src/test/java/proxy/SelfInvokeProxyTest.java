package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SelfInvokeProxyTest {

    @Test
    public void name() {
        String one = "abc";
        String two = new String("abc").intern();
//        two.intern();
        String three = "a"+"bc";
        System.out.println(one==two);
        System.out.println(one==three);
    }

    @Test
    public void should_invoke_inner_method() {
        SelfInvoke selfInvoke = new SelfInvoke() {
            @Override
            public String entry() {
                return "entry-" + exe();
            }

            @Override
            public String exe() {
                return "exe";
            }
        };
        SelfInvoke o = (SelfInvoke) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{SelfInvoke.class}, new MyInvokeHandler(selfInvoke));
        o.entry();
//        o.exe();
    }

    class MyInvokeHandler implements InvocationHandler {

        private SelfInvoke selfInvoke;

        public MyInvokeHandler(SelfInvoke selfInvoke) {
            this.selfInvoke = selfInvoke;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("start trx " + method.getName());
            Object invoke = method.invoke(selfInvoke, args);
            System.out.println("end trx " + method.getName());
            return invoke;
        }
    }
}
