package chongchong.wei.rxjava.rxjava01;

//定义发射数据的行为
public interface Emitter<T> {
    void onNext(T value);

    void onError(Throwable error);

    void onComplete();
}
