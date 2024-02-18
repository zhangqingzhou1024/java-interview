package com.interview.basic.io.bio.decoratormodel;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-18 00:18
 */
public class ComponetC extends Componet {

    Componet componet;

   public ComponetC(Componet componet){
        this.componet = componet;
    }

    @Override
    public void doSomeThing() {
        this.componet.doSomeThing();
        this.doOtherThing();
    }

    public void doOtherThing(){
        System.out.println("功能C");
    }
}
