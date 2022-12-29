package NewGenshin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    // todo 可不可以用map替代people？这东西没法维护，后续直接重构吧，现在能用就行
    static int 白字攻击力 = 924;
    static double 总攻击力 = 1962;
    static int 白字生命值 = 15552;
    static double 总生命值 = 27688;
    static double 反应系数 = 1;
    static String panel = "暴击71.9 暴伤146.3 充能271.2 充能30 增伤27";

    // 881 [次数]![buff] 叠层
    static String out = "攻击20 小攻击881 14!增伤3.5";
    // 英文符号 = !
    static String outDel = "1=2";

    // 技能增幅默认10级
    static boolean 雷神被动 = true; // 充能转化雷伤，把雷神下场后查看雷伤
    static boolean 绝缘4 = true;
    static boolean 胡桃e = false;
    static boolean 夜兰 = false;
    static boolean 护魔 = false; // 把胡桃下场后查看攻击力
    static boolean 薙刀 = true; // 把雷神下场后查看攻击力

    static boolean 展示圣遗物 = true;
    static boolean 展示最差 = false;

    static boolean 圣遗物显示垃圾 = false;
    static boolean showUppers = false; // debug用，展示垃圾为什么是垃圾

    static String[] flower = {
            "小攻击35 暴击7.8 暴伤19.4 攻击5.8", // 雷神
            "暴伤14 暴击9.3 小防御23 攻击9.3",
            "精通16 攻击16.3 暴击8.9 小攻击39",
            "攻击4.7 暴击9.3 小防御76 小攻击18",
            "攻击8.2 暴击7.4 小防御62 充能11",
            "小攻击18 小防御39 暴伤18.7 精通51",
            "暴击7.4 生命14.6 暴伤5.4 小防御56",
            "暴击2.7 防御20.4 暴伤12.4 充能9.1",
            "暴伤14 小防御39 暴击3.9 生命15.7",
            "暴伤19.4 小攻击18 暴击9.3 防御5.1",
            "暴击11.3 生命9.9 攻击4.7 精通42",
            "生命4.1 小防御32 暴伤18.7 攻击12.8"
    };
    static String[] feather = {
            "攻击5.8 暴击15.6 暴伤14 小生命269", // 雷神
            "暴击13.6 小防御23 小生命448 攻击9.9",
            "小防御58 攻击15.2 暴伤5.4 暴击6.6",
            "暴伤27.2 精通21 充能6.5 小防御65",
            "小生命269 暴伤22.5 精通61 暴击7.4",
            "暴伤12.4 防御16.8 小防御42 充能11.7",
            "小防御16 暴击6.2 攻击15.2 生命8.2",
            "小防御19 充能11 暴伤18.7 小生命508",
            "暴击6.6 小防御19 暴伤14 精通63",
            "攻击9.9 精通21 暴伤29.5 小防御23",
            "暴伤21 暴击9.3 精通19 充能5.8",
            "充能16.8 暴伤6.2 小生命448 攻击9.3"
    };
    static String[] hourglass = {
            "充能51.8 暴伤27.2 小攻击19 精通42 暴击2.7", // 雷神
            "攻击46.6 小防御46 暴击13.6 暴伤12.4 小攻击16",
            "充能51.8 暴伤13.2 小防御60 小生命269 防御12.4",
            "充能51.8 小防御16 生命15.7 暴击5.8 攻击14.6",
            "充能51.8 攻击19.8 生命10.5 防御7.3 小攻击37",
            "充能51.8 小攻击14 攻击4.1 小生命1195 生命9.9",
            "充能51.8 小防御21 防御13.9 暴伤21 生命9.9",
            "充能51.8 暴击5.8 攻击11.1 小防御37 防御13.9",
            "充能51.8 攻击9.3 生命5.8 小攻击33 小生命717",
            "攻击46.6 暴击5.8 生命13.4 防御10.2 精通35",
            "攻击46.6 暴伤12.4 暴击6.2 小攻击37 防御11.7",
            "生命46.6 攻击5.8 小防御42 暴击8.9 小攻击35",
            "生命46.6 暴伤7 小生命657 小攻击14 暴击8.2",
    };
    static String[] cup = {
            "攻击46.6 暴伤15.5 小生命269 暴击9.7 充能12.3", // 雷神
            "增伤46.6 暴击6.2 暴伤17.1 小生命418 精通33",
            "增伤46.6 小生命508 攻击9.3 暴击5.8 防御14.6",
            "增伤46.6 小生命747 精通21 攻击4.1 小攻击43",
            "增伤46.6 小防御37 暴击3.1 小生命807 防御19",
            "攻击46.6 小生命568 生命5.3 暴伤26.4 暴击6.6",
            "攻击46.6 小攻击14 生命18.1 精通47 充能10.4",
            "攻击46.6 小生命807 精通47 小防御16 充能10.4",
            "攻击46.6 暴击10.5 小攻击47 充能9.7 小生命209",
            "攻击46.6 小生命478 暴击7 充能5.8 暴伤20.2",
            "攻击46.6 防御15.3 暴伤5.4 暴击13.2 充能4.5",
            "攻击46.6 小生命418 暴伤13.2 暴击10.1 生命4.1",
            "攻击46.6 暴击6.2 小防御56 暴伤18.7 精通19",
            "攻击46.6 暴击10.9 小防御19 暴伤19.4 生命5.3",
    };
    static String[] head = {
            "暴击31.1 精通21 小攻击37 暴伤20.2 攻击10.5", // 雷神
            "暴击31.1 小防御37 小生命1225 充能5.2 防御6.6",
            "暴击31.1 小攻击29 小防御19 暴伤14 充能16.8",
            "暴击31.1 精通21 生命15.7 充能6.5 小生命866",
            "暴伤62.2 暴击9.7 小攻击16 攻击10.5",
            "攻击46.6 暴击6.2 防御14.6 小攻击54 小生命209",
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

        // 处理最差
        System.out.println("==========这里往下是最差=========");
        if (展示最差) {
            PreResult.handler(worseLink);
            result(worseLink);
        }

        // todo 显示垃圾圣遗物
        System.out.println("=================这里是显示垃圾圣遗物==================");
        if (圣遗物显示垃圾) {
            absolutelyRubbish(flower);
            absolutelyRubbish(feather);
            absolutelyRubbish(hourglass);
            absolutelyRubbish(cup);
            absolutelyRubbish(head);
        }
    }

    private static void absolutelyRubbish(String[] arr) {
        Set<String> lowers = new HashSet<>(arr.length);
        Set<String> uppers = new HashSet<>(arr.length);
        for (String s : arr) {
            Contrast res = new Contrast(s);
            // 边缘现象，子集无需判断
            if (lowers.contains(s)) {
                continue;
            }
            for (String s1 : arr) {
                if (!s.equals(s1)) {
                    String lower = res.contrast(s1);
                    if (lower != null) {
                        uppers.add(s);
                        lowers.add(lower);
                    }
                }
            }
        }
        System.out.println("结果集大小：" + lowers.size());
        for (String lower : lowers) {
            System.out.println(lower);
        }
        if (showUppers) {
            System.out.println("大于他的集合：" + uppers.size());
            for (String upper : uppers) {
                System.out.println(upper);
            }
        }
    }

    // 处理结果集
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

    // 添加外部
    void addOut(People p0, People outPeople, LinkList resultLinkP, LinkList worseLinkP, int index) {
        String[] arr = out.split(" ");
        String str;

        if (index < arr.length) {
            str = arr[index];
        } else {
            addFive(p0, outPeople, resultLinkP, worseLinkP);
            return;
        }

        // todo 新增带计数的
        if (str.contains("!")) {
            String[] split = str.split("!");
            int count = Integer.parseInt(split[0]);
            // 属性值字符串
            String value = split[split.length - 1];
            for (int i = 0; i <= count; i++) {
                StringBuilder sb = new StringBuilder(i * value.length() + i);
                for (int j = 0; j < i; j++) {
                    sb.append(value);
                    sb.append(" ");
                }

                outPeople.add(sb.toString());
                addOut(p0, outPeople, resultLinkP, worseLinkP, index + 1);
                outPeople.del(sb.toString());

            }
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
        worse[0] = Integer.MAX_VALUE;
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
