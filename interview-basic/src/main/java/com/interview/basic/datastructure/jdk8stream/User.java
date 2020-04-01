package com.interview.basic.datastructure.jdk8stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user对象
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-31 22:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * id
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别 1：男 -1： 女
     */
    private int sex;
    /**
     * 备注
     */
    private String remark;
}
