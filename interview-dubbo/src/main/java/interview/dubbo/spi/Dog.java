package interview.dubbo.spi;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 14:05
 */
public class Dog implements Animal {
    public String sayAnimalName() {
        return "dog";
    }
}
