package design.patterns.chain.responsibility;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author muco
 */
public class ChainFilter {
    public static void main(String[] args) throws IOException, InterruptedException {
        testWheelTimer();
    }

    private static void testWheelTimer() throws InterruptedException {
        Filter0 filter0 = new Filter0();
        Filter0 filter01 = new Filter0();
        Filter0 filter001 = new Filter0();
        FilterChain0 container = new FilterChain0();
        container.addFilter(filter0);
        container.addFilter(filter01);
        container.addFilter(filter001);
        new Thread(() -> {
            try {
                container.doExe(null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                container.doExe(null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static class Filter0 {
        public void doExe(Object req, Object resp, FilterChain0 chain) throws InterruptedException {
            System.out.println(String.format("[%s]before %s", Thread.currentThread().getName(), this));
            TimeUnit.SECONDS.sleep(1);
            chain.doExe(req, resp);
            System.out.println(String.format("[%s]after %s", Thread.currentThread().getName(), this));
        }
    }

    private static class FilterChain0 {
        private int cur = 0;
        private List<Filter0> filters = new ArrayList<>();
        private volatile boolean done = false;

        public synchronized void doExe(Object req, Object resp) throws InterruptedException {
            Filter0 filter0 = getFilter0();
            cur++;
            if (filter0 == null) {
                if (!done) {
                    System.out.println("do post");
                    done = true;
                }else{
                    System.out.println("nonsense");
                }
                return;
            }
            filter0.doExe(req, resp, this);
        }

        private Filter0 getFilter0() {
            try {
                return filters.get(cur);
            } catch (Exception e) {
                return null;
            }
        }

        public void addFilter(Filter0 filter0) {
            filters.add(filter0);
        }
    }
}
