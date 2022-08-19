package Genshin;

public class 圣遗物 {
    double 攻击;
    int 小攻击;
    double 生命;
    int 小生命;

    double 暴击;
    double 暴伤;
    int 精通;
    double 充能;
    double 防御;

    double 增伤;

    圣遗物(String str) {
        add(str);
    }

    void add(String str) {
        boolean flag = false;
        if (str.contains("小攻击")) {
            int index = str.indexOf("小攻击");
            小攻击 += adds(str, index + 3);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("小生命")) {
            int index = str.indexOf("小生命");
            小生命 += adds(str, index + 3);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("精通")) {
            int index = str.indexOf("精通");
            精通 += adds(str, index + 2);
            str = sub(str, index);
            flag = true;
        }

        if (str.contains("攻击")) {
            int index = str.indexOf("攻击");
            攻击 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("生命")) {
            int index = str.indexOf("生命");
            生命 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("暴击")) {
            int index = str.indexOf("暴击");
            暴击 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("暴伤")) {
            int index = str.indexOf("暴伤");
            暴伤 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("充能")) {
            int index = str.indexOf("充能");
            充能 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("防御")) {
            int index = str.indexOf("防御");
            防御 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (str.contains("增伤")) {
            int index = str.indexOf("增伤");
            增伤 += add(str, index + 2);
            str = sub(str, index);
            flag = true;
        }
        if (flag){
            add(str);
        }
    }

    String sub(String str, int index) {
        String s = str.substring(0, index);
        String e = "";
        if (str.indexOf(' ', index) != -1) {
            e = str.substring(str.indexOf(' ', index) + 1);
        }
        return s + e;
    }

    double add(String str, int index) {
        int end = str.indexOf(' ', index);
        String str0;
        if (end != -1) {
            str0 = str.substring(index, str.indexOf(' ', index));
        } else {
            str0 = str.substring(index);
        }
        return Double.parseDouble(str0);
    }

    int adds(String str, int index) {
        int end = str.indexOf(' ', index);
        String str0;
        if (end != -1) {
            str0 = str.substring(index, str.indexOf(' ', index));
        } else {
            str0 = str.substring(index);
        }
        return Integer.parseInt(str0);
    }
}
