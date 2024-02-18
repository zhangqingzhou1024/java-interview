package interview.lecode.dp;

import java.util.HashSet;

/**
 * 背包问题
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-02 16:41
 */
public class BagQuestion {

    public static void main(String[] args) {
        // 背包容量
        int bagMaxVolume = 8;

        Thing[] things = new Thing[4];
        Thing thing01 = new Thing(1, 2, 3);
        Thing thing02 = new Thing(2, 3, 4);
        Thing thing03 = new Thing(3, 4, 5);
        Thing thing04 = new Thing(4, 5, 6);

        things[0] = thing01;
        things[1] = thing02;
        things[2] = thing03;
        things[3] = thing04;


        int[][] optArr = dp_bag(things, bagMaxVolume);

        System.out.println( optArr[things.length][bagMaxVolume]);

        findIt(things,optArr,bagMaxVolume);
    }

    /**
     * 动态规划解题
     *
     * @param thingArr
     * @param bagMaxVolume
     * @return
     */
    public static int[][]  dp_bag(Thing[] thingArr, int bagMaxVolume) {
        int rows = thingArr.length + 1;
        int columns = bagMaxVolume + 1;
        int[][] optArr = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            optArr[i][0] = 0;
        }

        for (int i = 0; i < columns; i++) {
            optArr[0][i] = 0;
        }
        // 行表示 物品子集如 1：{1}, 2:{1,2}...
        for (int row = 1; row < rows; row++) {
            // 列表示 背包的容量
            for (int bagVolume = 1; bagVolume < columns; bagVolume++) {

                // 拿出子集内的东西
                Thing thing = thingArr[row - 1];
                int volume = thing.getVolume();
                int value = thing.getValue();

                // 1.不能装下这个物品
                if (bagVolume < volume) {
                    optArr[row][bagVolume] = optArr[row - 1][bagVolume];
                } else {
                    // 2. 如果可以装下这个物品，进行判断最大值
                    // 2.1 剩余余量可以装当前物品
                    int A = value + optArr[row - 1][bagVolume - volume];
                    // 2.2 不装当前物品
                    int B = optArr[row - 1][bagVolume];

                    int max = Math.max(A, B);
                    optArr[row][bagVolume] = max;
                }


            }

        }
        return optArr;
    }

    /**
     * 查询包里到底装的什么物品
     *
     * @param optArr
     * @return
     */
    public static HashSet findIt(Thing[] thingArr, int[][] optArr,int  bagMaxVolume) {

        int thingSerialNum = optArr.length;
        int bagVolume = optArr[0].length;

        int maxValue = optArr[thingSerialNum - 1][bagVolume - 1];

        for (int i = thingSerialNum; i > 0; i--) {

            int prevVal = optArr[i-1][bagVolume-1];
            Thing thing = thingArr[i-2];
            // != 表示 装了
            if(maxValue != prevVal){
                bagMaxVolume -= thing.getVolume();
                int value = thing.getValue();
                maxValue -= value;
                System.out.println(i);
            }



        }

        return null;
    }
}


class Thing {
    /**
     * 物品序号
     */
    int serialNum;

    /**
     * 物品体积
     */
    int volume;

    /**
     * 物品价值
     */
    int value;

    public Thing(int serialNum, int volume, int value) {
        this.serialNum = serialNum;
        this.volume = volume;
        this.value = value;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public int getVolume() {
        return volume;
    }

    public int getValue() {
        return value;
    }
}