package NewGenshin;

import java.util.Arrays;

public class Main {
    static int 白字攻击力 = 715;
    static double 总攻击力 = 1239;
    static int 白字生命值 = 15552;
    static double 总生命值 = 27668;
    static double 反应系数 = 1.5;
    static String panel = "暴击65.7 暴伤216.7 增伤46.6 护魔0.8 增伤50 精通310 攻击20";

    // 881
    static String out = "增伤33 护魔1 精通200";
    // 英文符号
    static String outDel = "1=2";

    // 技能增幅默认10级
    static boolean 雷神被动 = false; // 充能转化雷伤，把雷神下场后查看雷伤
    static boolean 绝缘4 = false;
    static boolean 胡桃e = true;
    static boolean 夜兰 = false;
    static boolean 护魔 = true; // 把胡桃下场后查看攻击力


    static boolean 展示圣遗物 = true;
    static boolean 展示最差 = true;


    static String[] flower = {
            "暴击3.5 生命19.2 小攻击14 暴伤18.7", // 胡桃
            "防御12.4 暴击13.2 暴伤6.2 小防御35",
            "暴伤26.4 生命5.3 暴击3.5 充能11.7",
            "暴伤11.7 暴击10.1 充能4.5 小攻击33",
            "生命15.7 充能5.8 暴伤5.4 精通63",
            "暴击5.4 防御19 生命4.1 小防御42",
            "暴伤20.2 攻击9.9 暴击3.5 小攻击31"
    };
    static String[] feather = {
            "精通82 小防御21 攻击5.8 暴击9.3", // 胡桃
            "精通40 充能4.5 小生命717 暴伤21",
            "精通37 暴伤21.8 攻击14 生命4.1",
            "暴伤11.3 生命9.3 精通16 攻击10.5",
            "暴击3.9 生命14 防御10.9 小生命538",
            "暴伤26.4 小防御42 暴击3.5 小生命269"
    };
    static String[] hourglass = {
            "精通187 暴伤22.5 防御46 暴击5.8 攻击4.1", // 胡桃
            "精通187 暴击10.1 小防御21 攻击9.9 小攻击33",
            "生命46.6 小攻击33 暴伤13.2 攻击15.2 暴击2.7",
            "生命46.6 小生命269 防御16.8 暴伤14 精通35",
            "生命46.6 小攻击51 充能5.8 防御6.6 暴击9.3",
    };
    static String[] cup = {
            "增伤46.6 小防御37 暴伤7.8 暴击10.9 小生命687", // 胡桃
            "增伤46.6 暴击6.6 防御12.4 暴伤19.4 攻击9.9",
            "增伤46.6 暴击2.7 暴伤12.4 小生命717 防御14.6",
            "增伤46.6 攻击5.3 小生命538 精通35 暴伤20.2",
            "增伤46.6 充能5.2 精通54 暴击7 防御10.2",
            "增伤46.6 小防御39 生命16.9 精通23 暴伤20.2",
            "增伤46.6 攻击14 暴击7.8 防御11.7 小防御23",
            "增伤46.6 暴击5.8 防御6.6 攻击20.4 小生命299",
            "增伤46.6 暴击3.5 充能15.5 小生命299 生命15.2",
            "增伤46.6 暴击6.6 防御12.4 暴伤19.4 攻击9.9",
            "增伤46.6 暴击3.1 精通77 防御5.1 小防御60",
            "增伤46.6 小攻击37 暴伤7.8 精通79 小生命299"
    };
    static String[] head = {
            "暴击31.1 暴伤13.2 小生命568 小防御37 精通42", // 胡桃
            "暴伤62.2 小生命448 攻击35 暴击6.2 攻击10.5",
            "精通187 生命4.7 小防御42 暴击6.6 攻击14",
            "暴伤62.2 小攻击53 精通68 暴击3.9 攻击4.7"
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
        PreResult.handler(resultLink);
        result(resultLink);

        System.out.println("==========这里往下是最差=========");
        // 处理最差
        if (展示最差) {
            PreResult.handler(worseLink);
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

            System.out.println("Main:106:康康所有数据" + Arrays.toString(p.data));

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

        System.out.println("============这里往下才是需要看的=============");
        System.out.println("min:" + Arrays.toString(min));
        if (展示圣遗物) {
            assert min != null;
            show(min);
        }
        System.out.println("max:" + Arrays.toString(max));
        if (展示圣遗物) {
            assert max != null;
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
