package chongchong.wei.rxjava.observer;

/**
 * 定义观察者的数据回调接口
 *
 * @param <T>
 */
public interface Observer<T> {
    void onSubscribe();

    void onNext(T value);

    void onError(Throwable e);

    void onComplete();
}
