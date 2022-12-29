package NewGenshin;

import java.util.LinkedList;
import java.util.List;

public class Suggest {

    // 权重，是这么叫吧
    public static final double[] baoji = {2.7, 3.1, 3.5, 3.9}; // 3.3
    public static final double[] shengming = {4.1, 4.7, 5.3, 5.8}; // 4.975
    public static final double[] gongji = shengming;
    public static final double[] chongneng = {4.5, 5.2, 5.8, 6.5}; // 5.5
    public static final double[] baoshang = {5.4, 6.2, 7, 7.8}; // 6.6
    public static final double[] jingtong = {16, 19, 21, 23}; // 19.75

    // 建议
    public static void suggest(String[] arr) {
        int min = Integer.MAX_VALUE;
        List<String> res = new LinkedList<>();

        for (String s : arr) {
            int count = count(new People(s));

            if (count <= min) {
                if (count > Main.rubbishLength) {
                    if (count < min) {
                        min = count;
                        res.clear();
                    }
                } else if (min > Main.rubbishLength) {
                    min = Main.rubbishLength;
                    res.clear();
                }
                res.add(s);
            }
        }

        // 打印
        if (min > Main.rubbishLength) {
            System.out.println("最差，但没到绝对差：" + min);
        }
        res.forEach(System.out::println);
    }

    // 获取中了几次
    private static int count(double value, double[] arr) {
        // 四舍五入估算法，只关心结果，不关心过程
        double avg = 0; // 平均值
        for (double v : arr) {
            avg += v;
        }
        avg /= arr.length;

        return (int) java.lang.Math.round(value / avg);
    }

    // 获取中了几次
    private static int count(People people) {
        int count = 0;
        if (Main.use攻击) {
            count += count(people.攻击 > 40 ? 0 : people.攻击, gongji);
        }
        if (Main.use暴击) {
            count += count(people.暴击 > 30 ? 0 : people.暴击, baoji);
        }
        if (Main.use暴伤) {
            count += count(people.暴伤 > 50 ? 0 : people.暴伤, baoshang);
        }
        if (Main.use充能) {
            count += count(people.充能 > 50 ? 0 : people.充能, chongneng);
        }
        if (Main.use生命) {
            count += count(people.生命 > 40 ? 0 : people.生命, shengming);
        }
        if (Main.use精通) {
            count += count(people.精通 > 150 ? 0 : people.精通, jingtong);
        }
        return count;
    }

    // 输入判断圣遗物是否值得
    private static int inputSuggest(String[] arr) {
        int count = Integer.MIN_VALUE;
        for (String s : arr) {
            count = java.lang.Math.max(count(new People(s)), count);
        }
        return count;
    }

    public static void inputSuggest(){
        System.out.println(inputSuggest(Main.feather));
        System.out.println(inputSuggest(Main.feather));
        System.out.println(inputSuggest(Main.hourglass));
        System.out.println(inputSuggest(Main.cup));
        System.out.println(inputSuggest(Main.head));
    }

}
