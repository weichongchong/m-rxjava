package chongchong.wei.rxjava.utils;

public class RLog {
    private static boolean enable = true;
    private static final String TAG = "RxJava";

    public static void setEnable(boolean enable) {
        RLog.enable = enable;
    }

    public static void printInfo(String msg) {
        if (enable) {
            System.out.println(TAG + getTreadName() + msg);
        }
    }

    public static void printError(String msg) {
        if (enable) {
            System.out.println(TAG + getTreadName() + msg);
        }
    }

    public static void printWarning(String message, Object... args) {
        if (enable) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            System.out.println(TAG + getTreadName() + message);
        }
    }


    private static String getTreadName() {
        return "[Thread: " + Thread.currentThread().getName() + "]_";
    }

}
