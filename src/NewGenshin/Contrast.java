package NewGenshin;

public class Contrast {

    public static final boolean 攻击 = true;
    public static final boolean 暴击 = true;
    public static final boolean 暴伤 = true;
    public static final boolean 充能 = true;
    public static final boolean 生命 = true;

    private People people;

    private String value;

    public String getValue() {
        return value;
    }

    public Contrast(People people) {
        this.people = people;
    }

    public Contrast(String value) {
        people = new People(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    // 是不是比他差
    public boolean contrast(People people) {
        int all = 0;
        int count = 0;
        if (攻击) {
            all++;
            if (people.攻击 <= this.people.攻击) {
                count++;
            }
        }

        if (暴击) {
            all++;
            if (people.暴击 <= this.people.暴击) {
                count++;
            }
        }

        if (暴伤) {
            all++;
            if (people.暴伤 <= this.people.暴伤) {
                count++;
            }
        }

        if (充能) {
            all++;
            if (people.充能 <= this.people.充能) {
                count++;
            }
        }

        if (生命) {
            all++;
            if (people.生命 <= this.people.生命) {
                count++;
            }
        }

        // 必须的东西
        all++;
        if (people.增伤 <= this.people.增伤) {
            count++;
        }

        return count == all;
    }

    // 比较
    public String contrast(String str) {
        if (contrast(new People(str))) {
            // 不如他
            return str;
        }
        return null;
    }

}