package com.interview.spring.simpleimpl.testspring;

import com.interview.spring.simpleimpl.util.ClassUtil;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-18 13:55
 */
public class TestClassUtil {
    @Test
    public void testClassUtil() {
        Set<Class<?>> packageClass = ClassUtil.getPackageClass("com.interview.spring.util");
        Iterator<Class<?>> iterator = packageClass.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
