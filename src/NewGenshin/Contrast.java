package NewGenshin;

public class Contrast {

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
        if (Main.use攻击) {
            all++;
            if (people.攻击 <= this.people.攻击) {
                count++;
            }
        }

        if (Main.use暴击) {
            all++;
            if (people.暴击 <= this.people.暴击) {
                count++;
            }
        }

        if (Main.use暴伤) {
            all++;
            if (people.暴伤 <= this.people.暴伤) {
                count++;
            }
        }

        if (Main.use充能) {
            all++;
            if (people.充能 <= this.people.充能) {
                count++;
            }
        }

        if (Main.use生命) {
            all++;
            if (people.生命 <= this.people.生命) {
                count++;
            }
        }

        if (Main.use精通) {
            all++;
            if (people.精通 <= this.people.精通) {
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