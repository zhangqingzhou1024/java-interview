package com.interview.basic.designmodel.adapter;

/**
 * 三线插头
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:36
 */
public interface TriplePin {
    //参数分别为火线live，零线null，地线earth
    void electrify(int l, int n, int e);
}
