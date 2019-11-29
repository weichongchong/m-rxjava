package chongchong.wei.rxjava.rxjava02;

import chongchong.wei.rxjava.rxjava02.observable.Observable;
import chongchong.wei.rxjava.rxjava02.observable.ObservableEmitter;
import chongchong.wei.rxjava.rxjava02.observable.ObservableOnSubscribe;
import chongchong.wei.rxjava.rxjava02.observer.Observer;

public class Test {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) {
                System.out.println("发射数据" + "1");
                observableEmitter.onNext("1");
                System.out.println("发射数据" + "2");
                observableEmitter.onNext("2");
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String value) throws Exception {
                return 1;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println("接收数据" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
