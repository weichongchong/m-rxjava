package chongchong.wei.rxjava.schedulers;

/**
 * 包名：chongchong.wei.rxjava.schedulers
 * 创建人：apple
 * 创建时间：2019-11-29 18:19
 * 描述：
 */
public final class Schedulers {
    public static final Scheduler IO = IoScheduler.getInstance();
    public static final Scheduler NEW_THREAD = NewThreadScheduler.getInstance();
    public static final Scheduler ANDROID_MAIN_THREAD = AndroidSchedulers.getInstance();

}
