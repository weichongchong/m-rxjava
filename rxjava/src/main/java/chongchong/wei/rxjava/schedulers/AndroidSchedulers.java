package chongchong.wei.rxjava.schedulers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 包名：chongchong.wei.rxjava.schedulers
 * 创建人：apple
 * 创建时间：2019-11-29 18:20
 * 描述：
 */
public class AndroidSchedulers extends Scheduler {

    private static final String TAG = "AndroidSchedulers";
    private final Handler handler = new Handler(Looper.getMainLooper());

    public static AndroidSchedulers getInstance() {
        return AndroidSchedulersHolder.INSTANCE;
    }

    private static class AndroidSchedulersHolder {
        private static AndroidSchedulers INSTANCE = new AndroidSchedulers();
    }

    @Override
    public void scheduleDirect(Runnable runnable) {
        Message message = Message.obtain(handler, runnable);
        message.obj = this;
        handler.sendMessage(message);
    }

}