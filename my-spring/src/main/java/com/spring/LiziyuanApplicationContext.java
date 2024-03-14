package com.spring;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zqz
 * @date 2024-03-13 23:50
 */
public class LiziyuanApplicationContext {

    private Class configCLass;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private List<BeanPostProcessor>  beanPostProcessorList= new ArrayList<>();

    public LiziyuanApplicationContext(Class configCLass) {
        this.configCLass = configCLass;

        // 扫描
        scan(configCLass);

        // 创建单例bean
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            BeanDefinition beanDefinition = beanDefinitionEntry.getValue();

            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }

    }

    // 创建bean
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        try {

            // 推断构造函数，依赖注入
            Object instance = clazz.getConstructor().newInstance();
            // 依赖注入
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Autowired fieldAnnotation = field.getAnnotation(Autowired.class);
                if (fieldAnnotation == null) {
                    continue;
                }
                // 这一步会有循环依赖
                String name = field.getName();
                field.set(instance, getBean(name));
            }

            // aware  ===> 可提取到 beanPostProcessor
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }
            if (instance instanceof ApplicationContextAware) {
                ((ApplicationContextAware)instance).setApplicationContext(this);
            }



            // 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            }
            
            // 初始化
            if(instance instanceof  InitializingBean){
                ((InitializingBean) instance).afterPropertiesSet();
            }

            // 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
               instance =  beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }


            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }


    private void scan(Class configCLass) {
        if (configCLass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = (ComponentScan) configCLass.getAnnotation(ComponentScan.class);
            String path = componentScan.value();
            path = path.replace(".", "/");

            ClassLoader classLoader = LiziyuanApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String absolutePath = f.getAbsolutePath();
                    System.out.println(absolutePath);

                    // 底层 ASM
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replaceAll("\\\\", ".");
                    try {
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        if (clazz.isAnnotationPresent(Component.class)) {

                            // beanPostProcessor
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                try {
                                    beanPostProcessorList.add((BeanPostProcessor) clazz.getConstructor().newInstance());
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            Component annotation = clazz.getAnnotation(Component.class);
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);

                            // bean
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotion = clazz.getAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotion.value());

                            } else {
                                // 单例
                                beanDefinition.setScope("singleton");
                            }
                            String beanName = annotation.value();
                            if (beanName.equals("")) {
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public Object getBean(String beanName) {

        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        String scope = beanDefinition.getScope();
        if (scope.equals("singleton")) {
            // 单例
            Object instance = singletonObjects.get(beanName);
            if (instance == null) {
                instance = createBean(beanName, beanDefinition);
            }
            return instance;
        } else {
            // 原型
            return createBean(beanName, beanDefinition);
        }
    }
}
