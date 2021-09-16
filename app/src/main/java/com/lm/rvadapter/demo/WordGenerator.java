package com.lm.rvadapter.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WordGenerator {
    private final static String[] words=new String[]{"abide","intelligence","aboard","abolish","abound","absorb","perceive","abandon","absurd","abundant"};

    public static List<String> generate(int amount){
        List<String> list=new ArrayList<>();
        int start= new Random().nextInt(words.length);
        for(int i=start,count=0;count<amount;i=++i%words.length){
            count++;
            list.add(words[i]);
        }
        return list;
    }
}
