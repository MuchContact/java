package annotation;


class MonitorOne implements IMonitor {
    private static String TAG = "MonitorOne";

    @Override
    public void before() {
        System.out.println(String.format("%s report: start to do something", TAG));
    }

    @Override
    public void after() {
        System.out.println(String.format("%s report: done for doing something", TAG));
    }
}
