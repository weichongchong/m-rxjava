package chongchong.wei.rxjava.rxjava02.observable;

//定义发射事件的行为
public interface Emitter<T> {
    void onNext(T value);

    void onError(Throwable error);

    void onComplete();
}
