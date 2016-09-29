package thread;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ThreadInterruptTest {

    @Test
    public void shouldThrowInterruptedExceptionWhenInterruptInsideRunBlock() throws InterruptedException {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        interruptThread.join();
        assertTrue(interruptThread.exception instanceof InterruptedException);
    }

    private class InterruptThread extends Thread {
        protected Exception exception;

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(2000);
                    interrupt();
                }
            } catch (InterruptedException e) {
                exception = e;
            }
        }
    }
}
