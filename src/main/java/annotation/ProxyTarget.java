package annotation;

class ProxyTarget {

    @MonitorAnnotation(monitor = MonitorOne.class)
    void doSomething() {
        System.out.println("Do your own work");
    }
}
