package NewGenshin;

import java.util.Arrays;

public class PreResult {

    private static int[] delArr;

    public static void handler(LinkList linkList) {
        long startTime = System.currentTimeMillis();
        int outLength = Main.out.split(" ").length;
        int listLength = 1 << outLength;
        delArr = new int[listLength + 1]; // 数组敲定都需要删除谁

        String[] delStrArr = Main.outDel.split(" ");
        for (String delStr : delStrArr) {
            if (delStr.contains("=")) {
                delXOR(Math.minMax(delStr, outLength));
            } else if (delStr.contains("!")) {
                delAnd(Math.minMax(delStr, outLength));
            } else {
                System.out.println("!!!!!PreResult 出问题了 017");
            }
        }

        // 枚举测试

        // 删除统计好全部然后从后往前删，设计上是这样的

        long endTime = System.currentTimeMillis();
        long fast = endTime - startTime;

        System.out.println("=============算法结果==============");
        System.out.println(Arrays.toString(delArr));
        System.out.println("=============枚举结果==============");

        startTime = System.currentTimeMillis();
        boolean conform = Test.conform(outLength, delStrArr, delArr);
        endTime = System.currentTimeMillis();
        long slow = endTime - startTime;

        System.out.println("节省了多少毫秒呢：" + (fast - slow) + "ms。草，负优化");
        System.out.println("==================================");

        assert conform;

        // 删除冗余部分
        for (int i = delArr.length - 1; i > 0; i--) {
            if (delArr[i] > 0){
                linkList.del(i);
            }
        }
    }

    private static void delAnd(int[] minMax) {
        int index = delArr.length - 1;
        int min = minMax[0];
        int max = minMax[1];

        int i = index;
        while (i > 0) {
            for (int k = 0; k < max / min >> 1; k++) {
                for (int j = 0; j < min; j++) {
                    delArr[i--]++;
                }
                i -= min;
            }
            i -= max;
        }

    }

    private static void delXOR(int[] minMax) {
        int index = delArr.length - 1;
        int min = minMax[0];
        int max = minMax[1];

        int i = index;
        boolean turn = true;
        while (i > 0) {
            for (int j = 0; j < max / min >> 1; j++) {
                if (turn) {
                    i -= min;
                }
                for (int k = 0; k < min; k++) {
                    delArr[i--]++;
                }
                if (!turn) {
                    i -= min;
                }
            }

            turn = !turn;
        }
    }

}
