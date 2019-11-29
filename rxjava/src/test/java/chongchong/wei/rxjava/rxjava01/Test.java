package chongchong.wei.rxjava.rxjava01;

public class Test {
    public static void main(String[] args) {
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
}
