package timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StateService {

    private HashedWheelTimer timer;
    private ExecutorService executorService;
    private Map<String, Timeout> tasks = new HashMap<>();
    private Set<String> offlines = Collections.synchronizedSet(new HashSet<>());

    @PostConstruct
    public void startTimer() {
        timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 1L, TimeUnit.SECONDS);
        timer.start();
        executorService = Executors.newFixedThreadPool(10);
    }

    public synchronized void update(String username) {
        System.out.println(String.format("[%s] %s 上线了", getCurTime(), username));
        offlines.remove(username);
        Timeout oldTimeout = tasks.get(username);
        if (oldTimeout != null) {
            oldTimeout.cancel();
        }
        Timeout timeout = timer.newTimeout(new OfflineTask(username, executorService, this), 10, TimeUnit.SECONDS);
        tasks.put(username, timeout);
    }

    public Object[] getOfflines() {
        return offlines.toArray();
    }

    static class OfflineTask implements TimerTask {
        private final String username;
        private final ExecutorService executorService;
        private final StateService stateService;

        public OfflineTask(String username, ExecutorService executorService, StateService stateService) {
            this.username = username;
            this.executorService = executorService;
            this.stateService = stateService;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            executorService.submit(() -> {
                System.out.println(String.format("[%s] %s 掉线了", getCurTime(), username));
                stateService.offlines.add(username);
            });
        }
    }

    private static String getCurTime() {
        String format = new SimpleDateFormat("HH:MM:ss").format(new Date());
        return format;
    }

}
