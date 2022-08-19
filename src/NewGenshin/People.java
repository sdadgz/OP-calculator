package NewGenshin;

public class People {
    private int 白字攻击力;
    private int 白字生命值;
    private double 反应系数;
    private double 总攻击;
    private double 总生命;

    private int 小攻击;
    private double 攻击;
    private int 小生命;
    private double 生命;
    private double 暴击;
    private double 暴伤;

    private double 防御;
    private int 小防御;

    private double 增伤;
    private int 精通;
    private double 充能;

    private double 护魔;

    public double 没暴击;
    public double 暴击了;

    People() {

    }

    People(People p) {
        add(p);
    }

    People(String str) {
        add(str);
    }

    People(String flower, String feather, String hourglass, String cup, String head) {
        add(flower);
        add(feather);
        add(hourglass);
        add(cup);
        add(head);
    }

    int compute() {
        double 精通增伤;
        总攻击 += (白字攻击力 * 攻击 / 100) + 小攻击;
        总生命 += (白字生命值 * 生命 / 100) + 小生命;

        if (Main.护魔) {
            总攻击 += 总生命 / 100 * 护魔;
        }
        if (Main.胡桃e) {
            总攻击 += 总生命 / 100 * 6.26;
        }
        if (Main.绝缘4) {
            if (充能 >= 300) {
                充能 = 300;
            }
            增伤 += 充能 / 4;
        }
        if (Main.夜兰) {
            总攻击 = 总生命;
        }
        if (Main.雷神被动) {
            double temp = 充能;
            temp -= 100;
            if (temp > 0) {
                temp = temp * 0.4;
                增伤 += temp;
            }
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

        double ans = 总攻击 * 暴击乘区 * 增伤乘区 * 反应乘区;
        int r = (int) (ans);

        double temp = r + 0.5;
        if (ans >= temp) {
            r++;
        }

//        double 技能倍率 = 177.4; // 变的，普工倍率177.4
//        double 角色等 = 90; // 变的，角色等级90
//        double 怪物等 = 84; // 变的，怪物等级84
//        double 等级压制 = (角色等 + 100) / (角色等 + 怪物等 + 200); // 计算公式，忽略他
//        double 怪物抗性_包括减抗 = get抗性(-20); // 变的，抗性-20
//        double 实际伤害需要的三个乘区 = 技能倍率 / 100 * 等级压制 * 怪物抗性_包括减抗; // 计算公式，忽略他
//        没暴击 = 总攻击 * 增伤乘区 * 反应乘区 * 实际伤害需要的三个乘区; // 计算公式，忽略他
//        暴击了 = 没暴击 * (暴伤 / 100 + 1); // 计算公式，忽略他

        return r;
    }

    private double get抗性(double i) {
        i /= 100;
        double result = 0;
        if (i < 0) {
            result = 1 - i / 2;
        } else {
            if (i < 0.75) {
                result = 1 - i;
            } else {
                result = 1 / (1 + 4 * i);
            }
        }
        return result;
    }

    // 设置初始值
    void set(int 白字攻击力, double 总攻击, int 白字生命值, double 总生命, double 反应系数) {
        this.白字攻击力 += 白字攻击力;
        this.总攻击 += 总攻击;
        this.总生命 += 总生命;
        this.白字生命值 += 白字生命值;
        this.反应系数 += 反应系数;
    }

    // 增加圣遗物，字符串输入
    void add(String str) {
        for (String s : str.split(" ")) {
            adds(s);
        }
    }

    // 增加圣遗物靠people
    void add(People p) {
        白字攻击力 += p.白字攻击力;
        白字生命值 += p.白字生命值;
        反应系数 += p.反应系数;
        总攻击 += p.总攻击;
        总生命 += p.总生命;
        小攻击 += p.小攻击;
        攻击 += p.攻击;
        小生命 += p.小生命;
        生命 += p.生命;
        暴击 += p.暴击;
        暴伤 += p.暴伤;
        防御 += p.防御;
        小防御 += p.小防御;
        增伤 += p.增伤;
        精通 += p.精通;
        充能 += p.充能;
        护魔 += p.护魔;
    }

    // 删除属性5
    public void del(String s1, String s2, String s3, String s4, String s5) {
        del(s1);
        del(s2);
        del(s3);
        del(s4);
        del(s5);
    }

    // 删除属性1
    void del(String str) {
        for (String s : str.split(" ")) {
            dels(s);
        }
    }


    // 单个属性增加到面板
    private void adds(String s) {
        if (s.contains("护魔")) {
            int index = s.indexOf("护魔");
            护魔 += subDou(s, index + 2);
            return;
        }
        if (s.contains("小攻击")) {
            int index = s.indexOf("小攻击");
            小攻击 += subNum(s, index + 3);
            return;
        }
        if (s.contains("小生命")) {
            int index = s.indexOf("小生命");
            小生命 += subNum(s, index + 3);
            return;
        }
        if (s.contains("精通")) {
            int index = s.indexOf("精通");
            精通 += subNum(s, index + 2);
            return;
        }

        if (s.contains("攻击")) {
            int index = s.indexOf("攻击");
            攻击 += subDou(s, index + 2);
            return;
        }
        if (s.contains("生命")) {
            int index = s.indexOf("生命");
            生命 += subDou(s, index + 2);
            return;
        }
        if (s.contains("暴击")) {
            int index = s.indexOf("暴击");
            暴击 += subDou(s, index + 2);
            return;
        }
        if (s.contains("暴伤")) {
            int index = s.indexOf("暴伤");
            暴伤 += subDou(s, index + 2);
            return;
        }
        if (s.contains("充能")) {
            int index = s.indexOf("充能");
            充能 += subDou(s, index + 2);
            return;
        }
        if (s.contains("小防御")) {
            int index = s.indexOf("小防御");
            小防御 += subNum(s, index + 3);
            return;
        }
        if (s.contains("防御")) {
            int index = s.indexOf("防御");
            防御 += subDou(s, index + 2);
            return;
        }
        if (s.contains("增伤")) {
            int index = s.indexOf("增伤");
            增伤 += subDou(s, index + 2);
            return;
        }
    }

    // 单个属性增加到面板
    private void dels(String s) {
        if (s.contains("护魔")) {
            int index = s.indexOf("护魔");
            护魔 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("小攻击")) {
            int index = s.indexOf("小攻击");
            小攻击 -= subNum(s, index + 3);
            return;
        }
        if (s.contains("小生命")) {
            int index = s.indexOf("小生命");
            小生命 -= subNum(s, index + 3);
            return;
        }
        if (s.contains("精通")) {
            int index = s.indexOf("精通");
            精通 -= subNum(s, index + 2);
            return;
        }

        if (s.contains("攻击")) {
            int index = s.indexOf("攻击");
            攻击 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("生命")) {
            int index = s.indexOf("生命");
            生命 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("暴击")) {
            int index = s.indexOf("暴击");
            暴击 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("暴伤")) {
            int index = s.indexOf("暴伤");
            暴伤 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("充能")) {
            int index = s.indexOf("充能");
            充能 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("小防御")) {
            int index = s.indexOf("小防御");
            防御 -= subNum(s, index + 3);
            return;
        }
        if (s.contains("防御")) {
            int index = s.indexOf("防御");
            防御 -= subDou(s, index + 2);
            return;
        }
        if (s.contains("增伤")) {
            int index = s.indexOf("增伤");
            增伤 -= subDou(s, index + 2);
            return;
        }
    }

    // 字符转数字
    private int subNum(String s, int index) {
        s = s.substring(index);
        return Integer.parseInt(s);
    }

    // 字符转小数
    private double subDou(String s, int index) {
        s = s.substring(index);
        return Double.parseDouble(s);
    }

    @Override
    public String toString() {
        return ("白功" + 白字攻击力 + ", " +
                "白生" + 白字生命值 + ", " +
                "反" + 反应系数 + ", " +
                "总攻" + 总攻击 + ", " +
                "总生" + 总生命 + ", " +
                "小功" + 小攻击 + ", " +
                "功" + 攻击 + ", " +
                "小生" + 小生命 + ", " +
                "生" + 生命 + ", " +
                "暴" + 暴击 + ", " +
                "伤" + 暴伤 + ", " +
                "防" + 防御 + ", " +
                "小防" + 小防御 + ", " +
                "增" + 增伤 + ", " +
                "精" + 精通 + ", " +
                "充" + 充能 +
                "护" + 护魔);
    }
}
