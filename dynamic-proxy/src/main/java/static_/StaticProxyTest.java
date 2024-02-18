package static_;

/**
 * @author zqz
 * @date 2024-01-31 10:09
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        // 创建被代理对象
        Student student = new Student();

        // 创建代理对象
        StudentProxy proxy = new StudentProxy(student);
        proxy.sendMsg("hello");
    }
}
