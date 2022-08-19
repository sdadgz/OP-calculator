package Genshin;

import java.util.Arrays;

public class 外界 {
    int length;
    单链表 data;
    单链表 p;

    外界(String str) {
        add(str);
    }

    void add(String str) {
        // 0空白，其余正常分割
        String[] arr = new String[length(str) + 1];
        arr[0] = "";
        slip(arr, str);
        data = new 单链表("");
        p = data;
        write(arr, "", 0);
        System.out.println(Arrays.toString(arr));
    }

    void write(String[] arr, String str, int index) {
        write(str);
        for (int i = index + 1; i < arr.length; i++) {
            write(arr, str + " " + arr[i], i);
        }
    }

    void write(String str) {
        p.set(str);
        p.next = new 单链表();
        p = p.next;
        length++;
    }

    void slip(String[] arr, String str) {
        int p = 1;
        while (true) {
            int index = str.indexOf(' ');
            if (index != -1) {
                arr[p++] = str.substring(0, index);
                str = str.substring(index + 1);
            } else {
                arr[p] = str;
                break;
            }
        }
    }

    int length(String str) {
        int p = 0;
        int count = 0;
        while (true) {
            int index = str.indexOf(' ', p);
            if (index != -1) {
                p = index + 1;
                count++;
            } else {
                break;
            }
        }
        return count + 1;
    }

    void print() {
        单链表 p = data;
        int count = 0;
        while (p != null) {
            System.out.println(count++ + " " + p.data);
            p = p.next;
        }
    }
}
