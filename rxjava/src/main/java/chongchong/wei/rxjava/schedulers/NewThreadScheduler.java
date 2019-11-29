package chongchong.wei.rxjava.schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 包名：chongchong.wei.rxjava.schedulers
 * 创建人：apple
 * 创建时间：2019-11-29 18:19
 * 描述：
 */
public class NewThreadScheduler extends Scheduler {
    private static final String TAG = "NewThreadScheduler";

    public static NewThreadScheduler getInstance(){
        return NewThreadSchedulerHolder.INSTANCE;
    }

    private static class NewThreadSchedulerHolder{
        private static NewThreadScheduler INSTANCE = new NewThreadScheduler();
    }

    @Override
    public void scheduleDirect(Runnable runnable) {
        executorService().execute(runnable);
    }

    private static ExecutorService executorService;

    private static synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), threadFactory
                    (false));
        }
        return executorService;
    }

    private static ThreadFactory threadFactory(final boolean daemon) {
        final AtomicInteger mCount = new AtomicInteger(1);
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread result = new Thread(runnable, "RxJava New Thread #" + mCount.getAndIncrement());
                result.setDaemon(daemon);
                return result;
            }
        };
    }

}
