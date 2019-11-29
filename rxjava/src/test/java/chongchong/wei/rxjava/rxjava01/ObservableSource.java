package chongchong.wei.rxjava.rxjava01;


/**
 * 定义被观察者的行为
 * @param <T>
 */
public interface ObservableSource<T> {
    void subscribe(Observer<? super T> observer);
}

