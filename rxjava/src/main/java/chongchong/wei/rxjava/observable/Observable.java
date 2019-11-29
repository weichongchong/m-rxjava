package chongchong.wei.rxjava.observable;


import chongchong.wei.rxjava.functions.Function;
import chongchong.wei.rxjava.observer.Observer;
import chongchong.wei.rxjava.schedulers.Scheduler;

/**
 * 被观察者
 * 提供发射事件的方法：通过构造方法传入的接口参数,将实际的发射对象回调出去，用户通过发射对象发射数据
 * <p>
 * 02更新：
 * 考虑到要对Observable做定制，如，各种操作符，对象转换，所以可以把Observable做成抽象类，具体实现交给子类
 * 提供一个对象创建的方法create,并提供一个单独的创建子类（把其它操作封装到创建子类中）
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate(source);
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        subscribeActual(observer);
    }

    //实际的处理过程交个子类实现
    protected abstract void subscribeActual(Observer<? super T> observer);

    public final <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
        return new ObservableMap<T, R>(this, mapper);
    }
    public final Observable<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<T>(this, scheduler);
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return new ObservableObserveOn<T>(this, scheduler);
    }

}
