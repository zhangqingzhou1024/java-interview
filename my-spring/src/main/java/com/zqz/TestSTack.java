package com.zqz;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zqz
 * @date 2024-03-15 13:11
 */
public class TestSTack {
    public static void main(String[] args) {
        String threadStack = "at org.springframework.context.annotation.ClassPathBeanDefinitionScanner.doScan(ClassPathBeanDefinitionScanner.java:268)\n" +
                "\tat org.springframework.context.annotation.ComponentScanAnnotationParser.parse(ComponentScanAnnotationParser.java:139)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:310)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:259)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:211)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:176)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:344)\n" +
                "\tat org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:248)\n" +
                "\tat org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:336)\n" +
                "\tat org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:132)\n" +
                "\tat org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:786)\n" +
                "\tat org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:582)\n" +
                "\tat org.springframework.context.annotation.AnnotationConfigApplicationContext.<init>(AnnotationConfigApplicationContext.java:95)";

        String[] stepArr = threadStack.split("\n");
        for (int i = stepArr.length - 1; i >= 0; i--) {
            System.out.println(stepArr[i].replaceFirst("at org.springframework.context.", ""));
        }

        String s = "  ddd   ";
        System.out.println(s.trim());
        int[][] ints = new int[2][2];
        //         ints[]
        //[[-2147483646,-2147483645],[2147483646,2147483647]]

        ints[0][0] = -2147483646;
        ints[0][1] = -2147483645;
        ints[1][0] = 2147483646;
        ints[1][1] = 21474836;
        Arrays.sort(ints,(x,y)-> x[0] - y[0]);
        System.out.println(ints);
        Arrays.sort(ints[1]);

        Arrays.sort(ints, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        System.out.println(ints);


        Arrays.sort(ints, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[0] > point2[0]) {
                    return 1;
                } else if (point1[0] < point2[0]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        Arrays.sort(ints, (point1, point2) -> {
            if (point1[0] > point2[0]) {
                return 1;
            } else if (point1[0] < point2[0]) {
                return -1;
            } else {
                return 0;
            }
        });

        System.out.println(ints);


    }
}
