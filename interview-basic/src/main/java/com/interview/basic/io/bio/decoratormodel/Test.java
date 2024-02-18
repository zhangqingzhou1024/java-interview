package com.interview.basic.io.bio.decoratormodel;

import java.io.BufferedInputStream;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-18 00:21
 */
public class Test {
    public static void main(String[] args) {
        Componet componet = new ComponetC(new Componet());
        componet.doSomeThing();

    }
}
