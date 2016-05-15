package annotation;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

class ProxyFactory {
    public static <T> T proxy(Class<T> proxyTargetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxyTargetClass);

        T t = newInstance(proxyTargetClass);
        InvocationHandler handler = (proxy, method, args) -> {
            MonitorAnnotation annotation = method.getAnnotation(MonitorAnnotation.class);
            Object result;
            if(annotation != null){
                Class monitor = annotation.monitor();
                IMonitor o = (IMonitor) newInstance(monitor);
                o.before();
                result = method.invoke(t, args);
                o.after();
            }else {
                result = method.invoke(t, args);
            }
            return result;
        };
        enhancer.setCallback(handler);
        T proxy = (T) enhancer.create();
        return proxy;
    }

    private static <T> T newInstance(Class<T> clazz) throws IllegalArgumentException{
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("Unable to initialize %s", clazz.getName()));
    }
}
