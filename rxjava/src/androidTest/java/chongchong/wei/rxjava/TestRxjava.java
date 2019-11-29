package chongchong.wei.rxjava;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import chongchong.wei.rxjava.functions.Function;
import chongchong.wei.rxjava.observable.Observable;
import chongchong.wei.rxjava.observable.ObservableEmitter;
import chongchong.wei.rxjava.observable.ObservableOnSubscribe;
import chongchong.wei.rxjava.observable.ObservableSource;
import chongchong.wei.rxjava.observer.Observer;
import chongchong.wei.rxjava.schedulers.Schedulers;
import chongchong.wei.rxjava.utils.CheckUtils;
import chongchong.wei.rxjava.utils.RLog;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestRxjava {
    @org.junit.Test
    public void useAppContext() {
        testThread();
    }

    private void testBase() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) {
                System.out.println("发射数据" + "1");
                observableEmitter.onNext("1");

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(String value) {
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

    private void testMap() {
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

    private void testThread() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                RLog.printInfo("emitter发送第一个onNext，value = 1");
                emitter.onNext(1);
                RLog.printInfo("emitter发送onComplete");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.NEW_THREAD).subscribeOn(Schedulers.IO).subscribeOn(Schedulers.NEW_THREAD).subscribeOn(Schedulers.IO).observeOn
                (Schedulers.IO).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                RLog.printInfo("切换线程");
                return "切换线程" + integer;
            }
        }).observeOn(Schedulers.ANDROID_MAIN_THREAD).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe() {
                RLog.printInfo("Observer被订阅");
            }

            @Override
            public void onNext(String value) {
                RLog.printInfo("Observer接收到onNext，被转换之后的value = " + value);
            }

            @Override
            public void onError(Throwable e) {
                RLog.printInfo("Observer接收到onError，errorMsg = " + e.getMessage());
            }

            @Override
            public void onComplete() {
                RLog.printInfo("Observer接收到onComplete");
            }
        });

    }
}
