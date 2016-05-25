package annotation;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

class ProxyFactory {
    public static <T> T proxy(Class<T> proxyTargetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxyTargetClass);
        enhancer.setCallback(generateCallback(proxyTargetClass));
        T proxy = (T) enhancer.create();
        return proxy;
    }

    private static <T> Callback generateCallback(Class<T> proxyTargetClass) {
        return new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                MonitorAnnotation annotation = method.getAnnotation(MonitorAnnotation.class);
                Object result;
                if(annotation != null){
                    Class monitorClazz = annotation.monitor();
                    IMonitor monitor = (IMonitor) newInstance(monitorClazz);
                    monitor.before();
                    result = proxy.invokeSuper(obj, args);
                    monitor.after();
                }else {
                    result = proxy.invokeSuper(obj, args);
                }
                return result;
            }
        };
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
