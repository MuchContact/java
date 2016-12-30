package thread;

import org.junit.Test;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by muco on 16/5/13.
 */
public class ThreadTest {
    @Test(expected = IllegalMonitorStateException.class)
    public void should_throw_exception() throws Exception {
        Object o = new Object();
        synchronized (o) {
            wait();
        }
    }

    @Test
    public void should_restart_after_notified() throws Exception {
        Target target = new Target();
        Thread thread = new Thread(target);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        //notify must happen after thread running
        target.notifyToStart();
        thread.join();
        assertThat(target.started, is(true));
    }

    private class Target implements Runnable{
        public boolean started;

        @Override
        public void run() {
            synchronized (this){
                try {
                    System.out.println("start to wait");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Started");
                started = true;
            }
        }

        public synchronized void notifyToStart() {
            System.out.println("notify");
            notifyAll();
        }
    }
}
