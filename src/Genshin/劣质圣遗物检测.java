package Genshin;

public class 劣质圣遗物检测 {
    boolean bj = true;
    boolean bs = true;
    boolean gj = true;

    boolean sm = false;
    boolean jt = false;

    boolean cn = false;
    boolean fy = false;

    单链表 p;

    double[] 暴击 = {0, 2.7, 3.1, 3.5, 3.9};
    double[] 暴伤 = {0, 5.4, 6.2, 7, 7.8};
    double[] 生命 = {0, 4.1, 4.7, 5.3, 5.8};
    int[] 精通 = {0, 16, 19, 21, 23};
    double[] 攻击 = {0, 4.1, 4.7, 5.3, 5.8};
    double[] 充能 = {0, 4.5, 5.2, 5.8, 6.5};
    double[] 防御 = {0, 5.1, 5.8, 6.6, 7.3};

    void test(String[] strArr) {
        for (String str : strArr) {
            test(str);
        }
    }

    void test(String str) {
        圣遗物 a = new 圣遗物(str);
    }
}
