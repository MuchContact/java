package thread.hook;

import java.util.concurrent.atomic.AtomicInteger;

public class RuntimeCleanup {
    static AtomicInteger counter = new AtomicInteger(0);
    static volatile boolean runningFlag = true;

    public static void main(String[] args) {
        Thread hardWorker = new Thread(() -> {
            while (runningFlag) {
                System.out.println("" + counter.getAndIncrement());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        hardWorker.start();
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                            runningFlag = false;
                            while (counter.get() > 0) {
                                System.out.println(String.format("xxxxxxxxxxxxxxx %d", counter.getAndDecrement()));
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );
    }
}
