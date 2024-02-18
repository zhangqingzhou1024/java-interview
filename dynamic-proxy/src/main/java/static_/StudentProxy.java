package static_;

/**
 * @author zqz
 * @date 2024-01-31 10:08
 */
public class StudentProxy implements Person {
    /**
     * 代理类提前在代码中确定被代理对象是学生
     */
    private Student student;

    public StudentProxy(Student stu) {
        this.student = stu;
    }

    @Override
    public void sendMsg(String msg) {
        // 代理对象增加额外工作
        System.out.println("static proxy do something...");

        // 通过代理对象调用被代理对象的方法
        student.sendMsg(msg);
    }
}
