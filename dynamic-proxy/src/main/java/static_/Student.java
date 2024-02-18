package static_;

/**
 * @author zqz
 * @date 2024-01-31 10:07
 */
public class Student implements Person{
    @Override
    public void sendMsg(String msg) {
        System.out.println("I say " + msg);
    }
}
