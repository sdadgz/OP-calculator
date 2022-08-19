package Genshin;

import java.util.Arrays;

public class Main {
    static int 白字攻击力 = 950;
    static int 总攻击力 = 2780;
    static int 白字生命值 = 13434;
    static int 总生命值 = 33026;//
    static double 反应系数 = 1;
    static String panel = "暴击71.5 暴伤139.7 增伤83.3";

    static String out = "攻击20 攻击40 增伤25 攻击9 攻击9 增伤30 小攻击881 攻击20 增伤24 增伤50";

    static boolean 绝缘4 = false;
    static boolean 胡桃e = false;
    static boolean 夜兰 = false;
    static boolean 展示圣遗物 = true;

    static String[] flower = {
    };
    static String[] feather = {
    };
    static String[] hourglass = {
    };
    static String[] cup = {
    };
    static String[] head = {
    };


    public static void main(String[] args) {
        外界 outside = new 外界(out);
        单链表 p = outside.data;
        单链表 ansArr = new 单链表();
        单链表 ap = ansArr;

        People p0 = new People(panel);
        p0.set(白字攻击力, 总攻击力, 白字生命值, 总生命值, 反应系数);
        p0.sub(flower[0], feather[0], hourglass[0], cup[0], head[0]);
        int[] ans = new int[6];


//        for (int i = 0; i < flower.length; i++) {
//            for (int j = 0; j < feather.length; j++) {
//                for (int k = 0; k < hourglass.length; k++) {
//                    for (int l = 0; l < cup.length; l++) {
//                        for (int m = 0; m < head.length; m++) {
//                            People temp = new People(flower[i], feather[j], hourglass[k], cup[l], head[m]);
//                            temp.add(p0);
//                            temp.add(p.data);
//                            int r = temp.compute();
//                            if (r > ans[0]) {
//                                ans[0] = r;
//                                ans[1] = i;
//                                ans[2] = j;
//                                ans[3] = k;
//                                ans[4] = l;
//                                ans[5] = m;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        ap.set(ans);
//        ap.next = new 单链表();
//        ap = ap.next;
//        p = p.next;
//            System.out.println(Arrays.toString(ans));
//        ans[0] = 0;
        必须写个递归辣(p0, p, ans, ap, outside.length);


        单链表 min = ansArr;
        单链表 max = ansArr;
        ap = ansArr;
        单链表 count = new 单链表();
        while (ap != null && ap.arr != null) {
            if (max.arr[0] < ap.arr[0]) {
                max = ap;
            }
            if (min.arr[0] > ap.arr[0]) {
                min = ap;
            }
            p = count;
            while (true) {
                if (p.arr == null) {
                    p.set(ap.arr);
                    p.arr[0] = 1;
                    break;
                }
                if (same(p.arr, ap.arr)) {
                    p.arr[0]++;
                    break;
                }
                if (p.next == null) {
                    p.next = new 单链表();
                }
                p = p.next;
            }
            ap = ap.next;
        }

//        outside.print();
        System.out.println("========================================");
        p = count;
        while (p != null && p.arr != null) {
            System.out.println(Arrays.toString(p.arr));
            if (展示圣遗物) {
                show(p.arr);
            }
            p = p.next;
        }
        System.out.println("========================================");
        System.out.println("min = " + Arrays.toString(min.arr));
        if (展示圣遗物) {
            show(min.arr);
        }
        System.out.println("max = " + Arrays.toString(max.arr));
        if (展示圣遗物) {
            show(max.arr);
        }
    }

    static void 必须写个递归辣(People p0, 单链表 p, int[] ans, 单链表 ap, int num) {
        if (num < 0) {
            for (int i = 0; i < flower.length; i++) {
                for (int j = 0; j < feather.length; j++) {
                    for (int k = 0; k < hourglass.length; k++) {
                        for (int l = 0; l < cup.length; l++) {
                            for (int m = 0; m < head.length; m++) {
                                People temp = new People(flower[i], feather[j], hourglass[k], cup[l], head[m]);
                                temp.add(p0);
//                            temp.add(p.data);
                                int r = temp.compute();
                                if (r > ans[0]) {
                                    ans[0] = r;
                                    ans[1] = i;
                                    ans[2] = j;
                                    ans[3] = k;
                                    ans[4] = l;
                                    ans[5] = m;
                                }
                            }
                        }
                    }
                }
            }
        }
        ap.set(ans);
        ap.next = new 单链表();
        ap = ap.next;
        p = p.next;
//            System.out.println(Arrays.toString(ans));
        ans[0] = 0;
    }

    static void show(int[] arr) {
        System.out.println(flower[arr[1]]);
        System.out.println(feather[arr[2]]);
        System.out.println(hourglass[arr[3]]);
        System.out.println(cup[arr[4]]);
        System.out.println(head[arr[5]]);
        System.out.println("====================");
    }

    static boolean same(int[] a1, int[] a2) {
        for (int i = 1; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
}
