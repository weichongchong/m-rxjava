package chongchong.wei.rxjava.rxjava02;

/**
 * 定义数据转换的接口，约定
 * @param <T>
 * @param <R>
 */
public interface Function<T, R> {
    // T 表示输入值，R 表示输出值,把T转换成R，
    R apply(T value) throws Exception;
}
