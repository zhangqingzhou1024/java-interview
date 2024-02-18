package com.interview.basic.designmodel.adapter;

/**
 * 适配器
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-19 23:39
 */
public class Adapter implements TriplePin {
    DualPin dualPin;

    Adapter(DualPin dualPin) {
        this.dualPin = dualPin;
    }

    @Override
    public void electrify(int l, int n, int e) {
        dualPin.electrify(l, n);
    }
}
