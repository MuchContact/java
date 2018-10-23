package design.patterns.chain.responsibility;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muco
 */
public class ChainFilter {
    public static void main(String[] args) throws IOException, InterruptedException {
        testWheelTimer();
    }

    private static void testWheelTimer() {
        Filter0 filter0 = new Filter0();
        Filter0 filter01 = new Filter0();
        Filter0 filter001 = new Filter0();
        FilterChain0 container = new FilterChain0();
        container.addFilter(filter0);
        container.addFilter(filter01);
        container.addFilter(filter001);
        container.doExe(null, null);
    }


    private static class Filter0 {
        public void doExe(Object req, Object resp, FilterChain0 chain) {
            System.out.println(String.format("before %s", this));
            chain.doExe(req, resp);
            System.out.println(String.format("after %s", this));
        }
    }

    private static class FilterChain0 {
        private int cur = 0;
        private List<Filter0> filters = new ArrayList<>();

        public void doExe(Object req, Object resp) {
            Filter0 filter0 = getFilter0();
            cur++;
            if (filter0 == null) {
                System.out.println("do post");
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
