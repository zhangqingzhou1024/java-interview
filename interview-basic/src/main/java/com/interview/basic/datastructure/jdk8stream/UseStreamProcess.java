package com.interview.basic.datastructure.jdk8stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 使用 stream 方式进行操作
 * stream 表达式去解决问题,代码简洁，链式处理
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-31 22:13
 */
public class UseStreamProcess {

    public static void main(String[] args) {
        /**
         *  User user01 = User.builder().age(21).id(1).name("大明").sex(1).remark("test").build();
         *  初始化userList进行mock数据
         *
         *  问题
         * 1. 根据性别进行分组
         * 2. 按照用户年龄进行排序（升序/降序）且取top3
         * 3. 选择出用户年龄中最大或最小的用户
         * 4. 挑选出年龄小于30岁的用户
         * 5. 统计出所有的用户名称（重复的算一个）
         * 6. 按用户Id进行分组，重复有重复要求进行覆盖（新旧数据中取较新的数据）
         * 7. 在用户列表中查询第一个姓名叫小明的并返回
         * 8. 选择用户年龄> 20 且性别为 男性的（sex=1）
         * 9. 选择条件性别为男性+用户年龄降序排序+最后按用户Id进行分组
         * 10. 在forEach 中对集合进行修改会起作用吗？
         * 11. 在stream 中进行操作数据，会对原始数据有影响吗？
         */

        // 初始化数据，具体在initDataList方法里
        List<User> userArrayList = initDataList();
        // 1. 根据性别进行分组
        Map<Integer, List<User>> listMap = userArrayList.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println(" 1. 根据性别进行分组");
        System.out.println(listMap);

        // 2. 按照用户年龄进行排序（升序/降序）且取top3  .sorted(Comparator.comparing(Student::getScore).reversed())
        List<User> sortedList = userArrayList.stream().sorted(Comparator.comparing(User::getAge).reversed()).limit(3).collect(Collectors.toList());
        System.out.println("2. 按照用户年龄进行排序（升序/降序）且取top3");
        sortedList.forEach(System.out::println);

        // 3. 选择出用户年龄中最大或最小的用户
        Optional<User> min = userArrayList.stream().min(Comparator.comparing(User::getAge));
        Optional<User> max = userArrayList.stream().max(Comparator.comparing(User::getAge));
        System.out.println("3. 选择出用户年龄中最大或最小的用户");
        System.out.println(max);
        System.out.println(min);

        // 4. 挑选出年龄小于30岁的用户
        List<User> userList = userArrayList.stream().filter(e -> e.getAge() < 30).collect(Collectors.toList());
        System.out.println("4. 挑选出年龄小于30岁的用户");
        userList.forEach(System.out::println);

        // 5. 统计出所有的用户名称（重复的算一个）
        List<String> userNameList = userArrayList.stream().map(User::getName).distinct().collect(Collectors.toList());
        // 或者用 toSet 方法
        Set<String> userNameSet = userArrayList.stream().map(User::getName).collect(Collectors.toSet());
        System.out.println("5. 统计出所有的用户名称（重复的算一个）");
        userNameList.forEach(System.out::println);
        System.out.println("5.2 - 用toSet方法处理");
        userNameSet.forEach(System.out::println);

        // 6.按用户Id进行分组，重复有重复要求进行覆盖（新旧数据中取较新的数据）
        Map<Integer, User> userIdMap = userArrayList.stream().collect(Collectors.toMap(User::getId, Function.identity(), (a, b) -> a));
        System.out.println("6.按用户Id进行分组，重复有重复要求进行覆盖（新旧数据中取较新的数据）");
        System.out.println(userIdMap);

        // 7.在用户列表中查询第一个姓名叫小明的并返回
        User user = userArrayList.stream().filter(u -> u.getName().equals("小明")).findFirst().orElse(null);
        System.out.println("7.在用户列表中查询第一个姓名叫小明的并返回");
        System.out.println(user);

        // 8.选择用户年龄> 20 且性别为 男性的（sex=1）
        List<User> resultList = userArrayList.stream().filter(u -> u.getAge() > 20 && u.getSex() == 1).collect(Collectors.toList());
        System.out.println("8.选择用户年龄> 20 且性别为 男性的（sex=1）");
        resultList.forEach(e -> {
            System.out.println(e.getId() + "," + e.getName());
        });

        // 9. 选择条件性别为男性+用户年龄降序排序+最后按用户Id进行分组
        Map<Integer, List<User>> resultMap = userArrayList.stream().filter(u -> u.getSex() == 1).sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.groupingBy(User::getId));
        System.out.println("9.选择条件性别为男性+用户年龄降序排序+最后按用户Id进行分组");
        System.out.println(resultMap);

        // 10. 在forEach 中对集合进行修改会起作用吗？
        userArrayList.forEach(e -> {
            e.setName("hello");
        });
        // 进行打印，看看所有的name 是不是 hello
        System.out.println("10. 在forEach 中对集合进行修改会起作用吗？ 会起作用的");
        userArrayList.forEach(System.out::println);

        // 11. 在stream 中进行操作数据，会对原始数据有影响吗？
        // 答案是不会影响的
        System.out.println("11. 在stream 中进行操作数据，会对原始数据有影响吗？ 并不会");


    }


    /**
     * 初始化数据
     *
     * @return 数据list
     */
    private static List<User> initDataList() {
        // 初始化数据
        User user01 = User.builder().age(21).id(1).name("大明").sex(1).remark("test").build();
        User user02 = User.builder().age(26).id(2).name("小红").sex(-1).remark("test1").build();
        User user03 = User.builder().age(31).id(3).name("小李").sex(-1).remark("test2").build();
        User user04 = User.builder().age(77).id(4).name("小风").sex(-1).remark("test33").build();
        User user05 = User.builder().age(19).id(5).name("小明").sex(1).remark("test78").build();
        User user06 = User.builder().age(58).id(3).name("小明").sex(1).remark("test78").build();

        // 追加进集合
        List<User> userArrayList = new ArrayList<>();
        userArrayList.add(user01);
        userArrayList.add(user02);
        userArrayList.add(user03);
        userArrayList.add(user04);
        userArrayList.add(user05);
        userArrayList.add(user06);

        return userArrayList;
    }
}
