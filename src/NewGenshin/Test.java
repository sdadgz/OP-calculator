package NewGenshin;

import java.util.Arrays;

public class Test {

    private static int[] resArr;

    public static boolean conform(int outLength, String[] strArr, int[] conformArr) {

        int[][] arr = initArr(outLength);

        System.out.println("========初始化map===========");
        System.out.println(Arrays.deepToString(arr));

        resArr = new int[1 << outLength];

        for (String str : strArr) {
            if (str.contains("=")) {
                delXOR(Math.simpleMinMax(str, outLength), arr);
            } else if (str.contains("!")) {
                delAnd(Math.simpleMinMax(str, outLength), arr);
            } else {
                System.out.println("Test:024:识别不到=或者!");
            }
        }

        int[] transArr = new int[resArr.length + 1];
        for (int i = 1; i < transArr.length; i++) {
            transArr[i] = resArr[i - 1];
        }

        System.out.println("============结果集==============");
        System.out.println(Arrays.toString(transArr));

        for (int i = 1; i < transArr.length; i++) {
            if (!(transArr[i] > 0 && conformArr[i] > 0 || (transArr[i] == 0 && conformArr[i] == 0))) {
                return false;
            }
        }

        return true;
    }

    private static void delAnd(int[] minMax, int[][] arr) {
        int min = minMax[0];
        int max = minMax[1];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][min] == arr[i][max] && arr[i][max] == 1) {
                resArr[i]++;
            }
        }

    }

    private static void delXOR(int[] minMax, int[][] arr) {
        int min = minMax[0];
        int max = minMax[1];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][min] != arr[i][max]) {
                resArr[i]++;
            }
        }
    }

    private static int[][] initArr(int length) {
        int[][] arr = new int[1 << length][length];

        new Test().setArr(arr, 0);

        return arr;
    }

    private void setArr(int[][] arr, int index) {
        int limMax = arr[0].length;
        if (index >= limMax) {
            return;
        }
        int step = 1 << index;
        int i = 0;
        while (i < arr.length) {
            i += step;
            for (int j = 0; j < step; j++) {
                arr[i][limMax - index - 1]++;
                i++;
            }
        }
        setArr(arr, index + 1);
    }
}
