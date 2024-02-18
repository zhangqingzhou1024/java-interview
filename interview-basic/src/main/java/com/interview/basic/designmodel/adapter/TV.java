package com.interview.basic.designmodel.adapter;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:38
 */
public class TV implements DualPin {
    @Override
    public//既然是两项插头，当然实现两项插标准
     void electrify(int l, int n) {
        System.out.println("火线通电：" + l);
        System.out.println("零线通电：" + n);
    }
}
