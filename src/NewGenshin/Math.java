package NewGenshin;

import java.util.Arrays;
import java.util.Collections;

public class Math {

    public static int[] minMax(String str, int length) {
        String[] strArr = str.split("[=!]");
        assert strArr.length == 2;
        int[] res = new int[2];
        for (int i = 0; i < strArr.length; i++) {
            res[i] = Integer.parseInt(strArr[i]);
        }

        // 倒序二进
        for (int i = 0; i < res.length; i++) {
            res[i] = 1 << length - res[i];
        }

        Arrays.sort(res);

        return res;
    }

    public static int[] simpleMinMax(String str, int outLength) {
        String[] strArr = str.split("[=!]");
        assert strArr.length == 2;
        int[] res = new int[2];
        for (int i = 0; i < strArr.length; i++) {
            res[i] = Integer.parseInt(strArr[i]);
        }

        for (int i = 0; i < res.length; i++) { // 倒叙
            res[i]--;
        }

        Arrays.sort(res);

        return res;
    }

    private static void change(int[] arr) {
        assert arr.length == 2;
        int temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }
}
