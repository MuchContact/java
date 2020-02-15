package reference;

import org.junit.Test;

import java.util.Objects;
import java.util.WeakHashMap;

public class WeakMapTest {
    @Test
    public void test_weak_map(){
        WeakHashMap<InnerTarget, String> target = new WeakHashMap<>();
        InnerTarget zhang = new InnerTarget("zhang");
        target.put(zhang, "doctor");
        zhang=null;
        System.gc();
        String zhang1 = target.get(new InnerTarget("zhang"));

    }


    private class InnerTarget {
        private String name;

        public InnerTarget(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InnerTarget that = (InnerTarget) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
