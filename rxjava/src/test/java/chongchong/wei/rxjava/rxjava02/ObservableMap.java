package chongchong.wei.rxjava.rxjava02;

import chongchong.wei.rxjava.rxjava02.observable.Observable;
import chongchong.wei.rxjava.rxjava02.observable.ObservableSource;
import chongchong.wei.rxjava.rxjava02.observer.Observer;
import chongchong.wei.rxjava.rxjava02.utils.CheckUtils;

/**
 * 装饰者模式-具体装饰者
 * function ：把装饰的部分操作交给用户定义，通过接口形式传递进来
 * source：被装饰的对象
 * @param <T>
 * @param <U>
 */
public class ObservableMap<T, U> extends Observable<U> {
    final Function<? super T, ? extends U> function;
    final ObservableSource<T> source;

    public ObservableMap(Observable<T> source, Function<? super T, ? extends U> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<? super U> observer) {
        MapObserver mapObserver = new MapObserver<T, U>(observer, function);
        source.subscribe(mapObserver);

    }

    static final class MapObserver<T, U> implements Observer<T> {
        protected final Observer<? super U> actual;
        final Function<? super T, ? extends U> mapper;

        public MapObserver(Observer<? super U> actual, Function<? super T, ? extends U> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(T value) {
            CheckUtils.checkNotNull(value, "onNext called parameter can not be null");
            U u = null;
            try {
                u = mapper.apply(value);//如何转换交给子类实现
            } catch (Exception e) {
                e.printStackTrace();
            }
            actual.onNext(u);
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
