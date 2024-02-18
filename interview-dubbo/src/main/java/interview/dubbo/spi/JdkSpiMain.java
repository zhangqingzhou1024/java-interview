package interview.dubbo.spi;

import com.sun.tools.javac.util.ServiceLoader;

import java.util.Iterator;

/**
 * jdk 原生服务
 * JDK SPI ServiceLoader缺点
 * 1.虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现类全部加载并实例化一遍。
 * 如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费。
 * 2.获取某个实现类的方式不够灵活，只能通过Iterator形式获取，不能根据某个参数来获取对应的实现类。
 * <p>
 * 链接：https://www.jianshu.com/p/7daa38fc9711
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 14:09
 */
public class JdkSpiMain {
    public static void main(String[] args) {
        // 文件路径在源码内一写死
        // private static final String PREFIX = "META-INF/services/";
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        for (Animal animal : animals) {
            System.out.println(animal.sayAnimalName());
        }

       /*
        cat
        dog
        */


    }
}
