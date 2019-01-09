package reference;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    @Test
    public void test_weak_reference() throws InterruptedException {
        SavePoint savePoint = new SavePoint("Random"); // a strong object

        ReferenceQueue<SavePoint> savepointQ = new ReferenceQueue<SavePoint>();// the ReferenceQueue
        WeakReference<SavePoint> savePointWRefernce = new WeakReference<SavePoint>(savePoint, savepointQ);

        System.out.println("SavePoint created as a weak ref " + savePointWRefernce);
        Runtime.getRuntime().gc();
        System.out.println("Any weak references in Q ? " + (savepointQ.poll() != null));
        savePoint = null; // the only strong reference has been removed. The heap
        // object is now only weakly reachable

        System.out.println("Now to call gc...");
        Runtime.getRuntime().gc(); // the object will be cleared here - finalize will be called.

        System.out.println(savePointWRefernce.get());
        Reference<? extends SavePoint> remove = savepointQ.remove();
        System.out.println("Any weak references in Q ? " + (remove != null));
        System.out.println("Does the weak reference still hold the heap object ? " + (savePointWRefernce.get() != null));
        System.out.println("Is the weak reference added to the ReferenceQ  ? " + (savePointWRefernce.isEnqueued()));
    }

    private static class SavePoint {
        private String random;

        public SavePoint(String random) {
            this.random = random;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize SavePoint");
        }
    }
}
