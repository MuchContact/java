package lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    @Test(expected = IllegalMonitorStateException.class)
    public void should_throw_exception() throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.unlock();
    }

}
