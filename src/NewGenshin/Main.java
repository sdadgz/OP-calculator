package NewGenshin;

import java.util.Arrays;

public class Main {
    static int 白字攻击力 = 950;
    static double 总攻击力 = 3063;
    static int 白字生命值 = 15552;
    static double 总生命值 = 27001;//
    static double 反应系数 = 1;
    static String panel = "暴击67.6 暴伤170 增伤83.3";

    // 881
    static String out = "";
    // 英文符号
    static String outDel = "";

    // 充能转化雷伤，panel写46.6或者0
    static boolean 雷神被动 = false;
    static boolean 绝缘4 = false;
    static boolean 胡桃e = false;
    static boolean 夜兰 = false;
    static boolean 护魔 = false;

    static boolean 展示圣遗物 = false;
    static boolean 展示最差 = false;


    static String[] flower = {
            "攻击11.1 暴伤19.4 暴击7.4", // 优菈
            "攻击4.7 暴伤29.5 小攻击29",
            "攻击9.9 暴伤21.8",
            "攻击10.5 暴击11.3 暴伤5.4",
    };
    static String[] feather = {
            "暴击7 暴伤6.2 攻击26.8", // 优菈
            "攻击9.3 暴击8.9",
            "攻击11.1 暴伤18.7 暴击7",
            "暴击9.7",
            "暴击17.9",
            "攻击5.3 暴伤14"
    };
    static String[] hourglass = {
            "攻击46.6 暴伤21 小攻击18 暴击5.4", // 优菈
            "攻击46.6 小攻击18 暴击9.7",
    };
    static String[] cup = {
            "增伤58.3 暴伤6.2 攻击4.7 暴击11.7", // 优菈
            "增伤58.3 攻击13.4",
            "增伤58.3 小攻击14 暴击14.8",
            "增伤58.3 小攻击14 暴伤21.8",
            "攻击46.6 暴伤15.5 暴击9.7",
            "攻击46.6 暴伤13.2 暴击10.1",
            "攻击46.6 暴击6.2 暴伤18.7",
            "攻击46.6 暴击10.9 暴伤19.4"
    };
    static String[] head = {
            "暴击31.1 攻击4.1 暴伤28.8", // 优菈
            "暴伤62.2 暴击6.6",
            "攻击46.6 暴伤27.2 暴击3.5",
            "暴击31.1 暴伤7.8", // 凝光
            "攻击46.6 暴击10.5 小攻击45",
            "暴击31.1 攻击14 小攻击16",
    };

    public static void main(String[] args) {
        // 初始化空圣遗物面板
        People p0 = new People(panel);
        p0.set(白字攻击力, 总攻击力, 白字生命值, 总生命值, 反应系数);
        p0.del(flower[0], feather[0], hourglass[0], cup[0], head[0]);

        // 计算期望
        LinkList resultLink = new LinkList();
        LinkList worseLink = new LinkList();
        new Main().addOut(p0, new People(), resultLink, worseLink, 0);

        System.out.println(resultLink);

        // 处理最好
        resultLink = PreResult.handler(resultLink);
        result(resultLink);

        System.out.println("==========这里往下是最差=========");
        // 处理最差
        if (展示最差) {
            worseLink = PreResult.handler(worseLink);
            result(worseLink);
        }
    }

    static void result(LinkList resultLinkP) {
        // 处理期望
        LinkList p = resultLinkP.next;
        // 最大期望和最小期望
        int[] min = p.data;
        int[] max = p.data;
        LinkList resultCount = new LinkList();
        while (p != null && p.data != null) {

            System.out.println("106" + Arrays.toString(p.data));
            System.out.println("107" + p.没暴击 + "::" + p.暴击了);

            if (p.data[0] > max[0]) { // 设置最大
                max = p.data;
            }
            if (p.data[0] < min[0]) { // 设置最小
                min = p.data;
            }

            LinkList cp = resultCount;
            while (true) {
                if (cp.data == null) {
                    cp.setData(p.data);
                    cp.data[0] = 1;
                    break;
                }
                if (cp.same(p)) {
                    cp.data[0]++;
                    break;
                }
                if (cp.next == null) {
                    cp.next = new LinkList();
                }

                cp = cp.next;
            }

            p = p.next;
        }

        p = resultCount;

        System.out.println("==================================");
        System.out.println("min:" + Arrays.toString(min));
        if (展示圣遗物) {
            show(min);
        }
        System.out.println("max:" + Arrays.toString(max));
        if (展示圣遗物) {
            show(max);
        }
        System.out.println("==================================");
        // 打印count
        while (p != null) {
            System.out.println("count:" + Arrays.toString(p.data));
            if (展示圣遗物) {
                show(p.data);
            }
            p = p.next;
        }
    }

    static void show(int[] ans) {
        System.out.println("    " + flower[ans[1]]);
        System.out.println("    " + feather[ans[2]]);
        System.out.println("    " + hourglass[ans[3]]);
        System.out.println("    " + cup[ans[4]]);
        System.out.println("    " + head[ans[5]]);
    }

    void addOut(People p0, People outPeople, LinkList resultLinkP, LinkList worseLinkP, int index) {
        String[] arr = out.split(" ");
        String str;

        if (arr.length > index) {
            str = arr[index];
        } else {
            addFive(p0, outPeople, resultLinkP, worseLinkP);
            return;
        }

        // 此处不加
        addOut(p0, outPeople, resultLinkP, worseLinkP, index + 1);
        // 此处加
        outPeople.add(str);
        addOut(p0, outPeople, resultLinkP, worseLinkP, index + 1);
        outPeople.del(str);
    }

    void addFive(People p0, People outPeople, LinkList resultLinkP, LinkList worseLinkP) {

        // 打印outPeople
//        System.out.println("outPeople对象属性" + outPeople);

        String str = null;
        int[] ans = new int[6];
        int[] worse = new int[6];
        worse[0] = 999999999;
        People temp = null;
        for (int i = 0; i < flower.length; i++) {
            for (int j = 0; j < feather.length; j++) {
                for (int k = 0; k < hourglass.length; k++) {
                    for (int l = 0; l < cup.length; l++) {
                        for (int m = 0; m < head.length; m++) {
                            temp = new People(flower[i], feather[j], hourglass[k], cup[l], head[m]);
                            // 增加去圣遗物的人物属性
                            temp.add(p0);
                            // 增加外部属性
                            temp.add(outPeople);
                            int r = temp.compute();

                            if (r < worse[0]) {
                                worse[0] = r;
                                worse[1] = i;
                                worse[2] = j;
                                worse[3] = k;
                                worse[4] = l;
                                worse[5] = m;
                            }

                            if (r > ans[0]) {
                                str = temp.toString();
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

        // 打印最佳圣遗物面板
//        System.out.println(str);

        // 加入到结果集
        while (resultLinkP.next != null) {
            resultLinkP = resultLinkP.next;
        }
        resultLinkP.next = new LinkList();
        resultLinkP = resultLinkP.next;
        resultLinkP.data = ans;

        resultLinkP.没暴击 = temp.没暴击;
        resultLinkP.暴击了 = temp.暴击了;


        // 加入到结果集
        while (worseLinkP.next != null) {
            worseLinkP = worseLinkP.next;
        }
        worseLinkP.next = new LinkList();
        worseLinkP = worseLinkP.next;
        worseLinkP.data = worse;

    }
}
