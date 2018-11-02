package lock;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        new Thread(() -> {
            boolean b = false;
            while (!b) {
                b = reentrantLock.tryLock();
                System.out.println(String.format("Getting lock : %b", b));
            }

            reentrantLock.unlock();
        }).start();
        TimeUnit.SECONDS.sleep(2);
        reentrantLock.unlock();
    }
}
