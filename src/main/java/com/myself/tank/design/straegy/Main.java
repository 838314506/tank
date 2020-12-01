package com.myself.tank.design.straegy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Cat[] cats = {new Cat(1,1),new Cat(3,3),new Cat(5,5)};
        Dog[] dogs = {new Dog(1),new Dog(3),new Dog(5)};

//        Sort<Cat> sort = new Sort<>();
//        sort.sort(cats,(o1,o2) -> {
//            if (o1.weight > o2.weight) return -1;
//            else if (o1.weight == o2.weight) return 0;
//            else return 1;
//        });
//
        Sort<Dog> d = new Sort<>();
        d.sort(dogs,(o1,o2) -> {
            if (o1.foods > o2.foods) return -1;
            else if (o1.foods == o2.foods) return 0;
            else return 1;
        });
        System.out.println(Arrays.toString(dogs));


//        System.out.println(Arrays.toString(cats));
    }
}
