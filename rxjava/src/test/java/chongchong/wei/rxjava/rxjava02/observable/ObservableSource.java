package chongchong.wei.rxjava.rxjava02.observable;


import chongchong.wei.rxjava.rxjava02.observer.Observer;

/**
 * 定义被观察者的行为
 * @param <T>
 */
public interface ObservableSource<T> {
    void subscribe(Observer<? super T> observer);
}

