package chongchong.wei.rxjava.observable;


import chongchong.wei.rxjava.observer.Observer;

public final class ObservableCreate<T> extends Observable<T> {
    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        CreateEmitter<T> createEmitter = new CreateEmitter<T>(observer);
        observer.onSubscribe();
        try {
            source.subscribe(createEmitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //把ObservableEmitter 分发的事件分发给observer

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
