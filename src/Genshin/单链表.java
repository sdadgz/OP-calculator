package Genshin;

public class 单链表 {
    String data;
    单链表 next;
    int[] arr;

    单链表(String str) {
        set(str);
    }

    单链表() {
    }

    void set(String str) {
        data = str;
    }

    void set(int[] arr) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }
}
