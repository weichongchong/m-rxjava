package chongchong.wei.rxjava.observable;

import chongchong.wei.rxjava.observer.Observer;
import chongchong.wei.rxjava.schedulers.Scheduler;
import chongchong.wei.rxjava.utils.CheckUtils;
import chongchong.wei.rxjava.utils.RLog;

/**
 * 包名：chongchong.wei.rxjava.observable
 * 创建人：apple
 * 创建时间：2019-11-29 18:24
 * 描述：
 */
public class ObservableSubscribeOn<T> extends Observable<T> {

    final Scheduler scheduler;
    final ObservableSource<T> source;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    public final ObservableSource<T> source() {
        return source;
    }

    @Override
    public void subscribeActual(final Observer<? super T> s) {
        final SubscribeOnObserver<T> parent = new SubscribeOnObserver<T>(s);
        scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                RLog.printInfo("我在这里切换");
                source.subscribe(parent);
            }
        });
    }


    static final class SubscribeOnObserver<T> implements Observer<T> {
        final Observer<? super T> actual;

        SubscribeOnObserver(Observer<? super T> actual) {
            this.actual = actual;
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            CheckUtils.checkNotNull(t, "onNext called parameter can not be null");
            actual.onNext(t);
        }

        @Override
        public void onError(Throwable error) {
            actual.onError(error);

        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }

    }
}

