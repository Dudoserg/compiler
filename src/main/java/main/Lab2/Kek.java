package main.Lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kek {
    List<Integer> list = new ArrayList<>();

    public Kek(List<Integer> list) {
        this.list = list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < 1000; i++){
            list.add(i);
        }
        Kek kek = new Kek(list);

        for(int i = 0 ; i < 1000; i++){
            for(int j = 0 ; j < 1000; j++){
                Kek result = kek.action_plus(2).action_minus(2);
            }
            System.out.println(i);
        }
    }


    public Kek action_plus(int plus) {
        List<Integer> new_list = new ArrayList<>(Collections.nCopies(list.size(), -1));
        for (int i = 0; i < this.list.size(); i++) {
            new_list.set(i, this.list.get(i) + plus);
        }
        return new Kek(new_list);
    }

    public Kek action_minus(int minus) {
        List<Integer> new_list = new ArrayList<>(Collections.nCopies(list.size(), -1));
        for (int i = 0; i < this.list.size(); i++) {
            new_list.set(i, this.list.get(i) - minus);
        }
        return new Kek(new_list);
    }

    public Kek action_muv(int muv) {
        List<Integer> new_list = new ArrayList<>(Collections.nCopies(list.size(), -1));
        for (int i = 0; i < this.list.size(); i++) {
            new_list.set(i, this.list.get(i) * muv);
        }
        return new Kek(new_list);
    }
}
