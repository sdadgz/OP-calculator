package Genshin;

public class People {
    int 白字攻击力;
    int 白字生命值;
    double 反应系数;
    double 总攻击;
    double 总生命;

    int 小攻击;
    double 攻击;
    int 小生命;
    double 生命;

    double 暴击;
    double 暴伤;

    double 增伤;
    int 精通;
    double 充能;

    People(String str) {
        add(str);
    }

    People(String flower, String feather, String hourglass, String cup, String head) {
        add(flower, feather, hourglass, cup, head);
    }

    int compute() {
        double 精通增伤;
        总攻击 += (白字攻击力 * 攻击 / 100) + 小攻击;
        总生命 += (白字生命值 * 生命 / 100) + 小生命;

        if (Main.胡桃e) {
            总攻击 += 总生命 / 100 * 6.26;
        }
        if (Main.绝缘4) {
            if (充能 >= 300){
                充能 = 300;
            }
            增伤 += 充能 / 4;
        }
        if (Main.夜兰) {
            总攻击 = 总生命;
        }

        double b = 暴击 / 100;
        if (b >= 1) {
            b = 1;
        }
        double s = 暴伤 / 100;
        double 暴击乘区 = b * (s + 1) + (1 - b);
        double 增伤乘区 = 增伤 / 100 + 1;
        double 反应乘区 = 1;
        if (反应系数 >= 1.5) {
            精通增伤 = 278. * 精通 / (精通 + 1400);
            精通增伤 /= 100;
            反应乘区 = 反应系数 * (精通增伤 + 1);
        }
        int r = (int) (总攻击 * 暴击乘区 * 增伤乘区 * 反应乘区);
        return r;
    }

    void set(int 白字攻击力, int 总攻击, int 白字生命值, int 总生命, double 反应系数) {
        this.白字攻击力 += 白字攻击力;
        this.总攻击 += 总攻击;
        this.总生命 += 总生命;
        this.白字生命值 += 白字生命值;
        this.反应系数 += 反应系数;
    }

    void add(People _0) {
        白字攻击力 += _0.白字攻击力;
        白字生命值 += _0.白字生命值;
        总攻击 += _0.总攻击;
        总生命 += _0.总生命;
        反应系数 += _0.反应系数;

        小攻击 += _0.小攻击;
        攻击 += _0.攻击;
        小生命 += _0.小生命;
        生命 += _0.生命;

        暴击 += _0.暴击;
        暴伤 += _0.暴伤;

        精通 += _0.精通;
        充能 += _0.充能;
        增伤 += _0.增伤;
    }

    void add(String flower, String feather, String hourglass, String cup, String head) {
        add(flower);
        add(feather);
        add(hourglass);
        add(cup);
        add(head);
    }

    void add(String str) {
        圣遗物 _0 = new 圣遗物(str);
        add(_0);
    }

    void add(圣遗物 _0) {
        小攻击 += _0.小攻击;
        攻击 += _0.攻击;
        小生命 += _0.小生命;
        生命 += _0.生命;
        暴击 += _0.暴击;
        暴伤 += _0.暴伤;
        精通 += _0.精通;
        充能 += _0.充能;
        增伤 += _0.增伤;
    }

    void sub(String _1, String _2, String _3, String _4, String _5) {
        sub(_1);
        sub(_2);
        sub(_3);
        sub(_4);
        sub(_5);
    }

    void sub(String str) {
        圣遗物 _0 = new 圣遗物(str);
        sub(_0);
    }

    void sub(圣遗物 _0) {
        小攻击 -= _0.小攻击;
        攻击 -= _0.攻击;
        小生命 -= _0.小生命;
        生命 -= _0.生命;
        暴击 -= _0.暴击;
        暴伤 -= _0.暴伤;
        精通 -= _0.精通;
        充能 -= _0.充能;
        增伤 -= _0.增伤;
    }
}
