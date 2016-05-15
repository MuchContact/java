package annotation;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

class ProxyFactory {
    public static <T> T proxy(Class<T> proxyTargetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxyTargetClass);
        enhancer.setCallback(generateInvocationHandler(proxyTargetClass));
        T proxy = (T) enhancer.create();
        return proxy;
    }

    private static <T> InvocationHandler generateInvocationHandler(Class<T> proxyTargetClass) {
        T originInstance = newInstance(proxyTargetClass);
        return (proxy, method, args) -> {
            MonitorAnnotation annotation = method.getAnnotation(MonitorAnnotation.class);
            Object result;
            if(annotation != null){
                Class monitorClazz = annotation.monitor();
                IMonitor monitor = (IMonitor) newInstance(monitorClazz);
                monitor.before();
                result = method.invoke(originInstance, args);
                monitor.after();
            }else {
                result = method.invoke(originInstance, args);
            }
            return result;
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
