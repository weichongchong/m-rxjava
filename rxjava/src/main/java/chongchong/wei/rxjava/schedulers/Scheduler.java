package chongchong.wei.rxjava.schedulers;

/**
 * 包名：chongchong.wei.rxjava.schedulers
 * 创建人：apple
 * 创建时间：2019-11-28 17:25
 * 描述：
 */
public abstract class Scheduler {
    public abstract void scheduleDirect(Runnable runnable);
}
