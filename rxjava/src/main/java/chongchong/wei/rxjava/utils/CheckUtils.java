package chongchong.wei.rxjava.utils;

public class CheckUtils {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference,  Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    public static void checkError(boolean isError,  Object errorMessage) {
        if (isError) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
    }

}
