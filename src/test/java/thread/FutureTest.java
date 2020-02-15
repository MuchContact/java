package thread;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {
    @Test
    public void should_call_future() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String s = "hello callable";
                System.out.println(s);
                return s;
            }
        });
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        String s = submit.get();
        System.out.println(">>>>>>>>>>>>>" + s);
    }
}
