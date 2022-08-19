package NewGenshin;

import java.util.Arrays;

public class LinkList {
    LinkList next;

    int[] data;
    double 没暴击;
    double 暴击了;

    public void setData(int[] data) {
        this.data = new int[data.length];
        this.data[1] = data[1];
        this.data[2] = data[2];
        this.data[3] = data[3];
        this.data[4] = data[4];
        this.data[5] = data[5];
    }

    public boolean same(LinkList node) {
        for (int i = 1; i < data.length; i++) {
            if (node.data[i] != data[i]) {
                return false;
            }
        }
        return true;
    }

    public void del(int index) {
        if (index <= 1) { // 到1删除
            this.next = this.next.next;
        } else {
            this.next.del(index - 1);
        }
    }

    @Override
    public String toString() {
        String thisStr = "no_data";
        if (data != null){
            thisStr = Arrays.toString(data);
        }
        String nextStr = "end";
        if (next != null) {
            nextStr = next.toString();
        }
        return thisStr +"||" + nextStr;
    }
}
