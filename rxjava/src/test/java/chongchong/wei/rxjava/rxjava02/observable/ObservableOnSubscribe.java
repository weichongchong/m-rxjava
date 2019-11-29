package chongchong.wei.rxjava.rxjava02.observable;

import chongchong.wei.rxjava.rxjava02.observable.ObservableEmitter;

/**
 * 这个接口的作用就是
 * 把创建的ObservableEmitter这个发射器实例回调出去，用户就可以使用这个发射器发射数据了
 */
public interface ObservableOnSubscribe<T> {
    void subscribe(ObservableEmitter<T> observableEmitter) throws Exception;
}
