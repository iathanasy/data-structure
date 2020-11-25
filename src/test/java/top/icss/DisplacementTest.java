package top.icss;

/**
 * 位移 测试
 * @author cd.wang
 * @create 2020-11-19 16:06
 * @since 1.0.0
 */
public class DisplacementTest {
    public static void main(String[] args) {
        // 右 10 >> 1 除以 2 的 1次方
        // 右 10 >> 2 除以 2 的 2次方
        // 右 10 >> 3 除以 2 的 3次方
        System.out.println(10 >> 1);  // 5
        System.out.println(10 >> 2);  // 2
        System.out.println(10 >> 3);  // 1
        System.out.println(10 >> 4);  // 0
        System.out.println(10 >> 5);  // 0
        // 左 10 << 1 剩以 2 的 1 次方
        // 左 10 << 2 剩以 2 的 2 次方
        // 左 10 << 2 剩以 2 的 3 次方
        System.out.println(10 << 1);
        System.out.println(10 << 2);
        System.out.println(10 << 3);
        System.out.println(10 << 4);
        System.out.println(10 << 5);
    }
}
