package chongchong.wei.rxjava.rxjava01;


import chongchong.wei.rxjava.rxjava02.observable.ObservableCreate;

/**
 * 被观察者
 * 提供发射数据的方法：通过create()方法传入的接口参数,将实际发射数据的对象回调出去，用户通过发射对象发射数据
 */
public class Observable<T> implements ObservableSource<T> {
    final ObservableOnSubscribe source;//类似接口监听，作用是把观察者事件发射的对象，回调出去

    private Observable(ObservableOnSubscribe source) {
        this.source = source;
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new Observable(source);
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        CreateEmitter<T> createEmitter = new CreateEmitter<T>(observer);
        observer.onSubscribe();
        source.subscribe(createEmitter);//把被观察者发射数据的对象回调出去
    }


    //定义被观察者发射数据的对象，并把数据分发给观察者observer
    static final class CreateEmitter<T> implements ObservableEmitter<T> {
        final Observer<? super T> observer;

        public CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T value) {
            observer.onNext(value);
        }

        @Override
        public void onError(Throwable error) {
            onError(error);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }

}
