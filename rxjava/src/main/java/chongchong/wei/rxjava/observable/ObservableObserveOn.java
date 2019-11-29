package chongchong.wei.rxjava.observable;

import chongchong.wei.rxjava.observer.Observer;
import chongchong.wei.rxjava.schedulers.Scheduler;
import chongchong.wei.rxjava.utils.CheckUtils;

/**
 * 包名：chongchong.wei.rxjava.observable
 * 创建人：apple
 * 创建时间：2019-11-29 18:22
 * 描述：
 */
public class ObservableObserveOn<T> extends Observable<T> {

    final Scheduler scheduler;
    final ObservableSource<T> source;

    public ObservableObserveOn(ObservableSource<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    public final ObservableSource<T> source() {
        return source;
    }

    @Override
    public void subscribeActual(final Observer<? super T> s) {
        final ObserveOnObserver<T> parent = new ObserveOnObserver<T>(s, scheduler);
        source.subscribe(parent);

    }

    static final class ObserveOnObserver<T> implements Observer<T> {
        final Observer<? super T> actual;
        final Scheduler scheduler;

        public ObserveOnObserver(Observer<? super T> actual, Scheduler scheduler) {
            this.actual = actual;
            this.scheduler = scheduler;
        }

        @Override
        public void onSubscribe() {
            scheduler.scheduleDirect(new Runnable() {
                @Override
                public void run() {
                    actual.onSubscribe();
                }
            });
        }

        @Override
        public void onNext(final T t) {
            scheduler.scheduleDirect(new Runnable() {
                @Override
                public void run() {
                    CheckUtils.checkNotNull(t, "onNext called parameter can not be null");
                    actual.onNext(t);
                }
            });
        }

        @Override
        public void onError(final Throwable error) {
            scheduler.scheduleDirect(new Runnable() {
                @Override
                public void run() {
                    actual.onError(error);
                }
            });
        }

        @Override
        public void onComplete() {
            scheduler.scheduleDirect(new Runnable() {
                @Override
                public void run() {
                    actual.onComplete();
                }
            });
        }
    }

}
