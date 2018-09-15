package main.java;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date start = new Date();
        FileLoader loader = new FileLoader();
        BinaryTree numbers = loader.getNumbers();
        numbers.handleData();
        System.out.println("Minimum : "+numbers.getMin());
        System.out.println("Maximum : "+numbers.getMax());
        System.out.println("Median : " + numbers.getMedian());
        System.out.println("Average arithmetic : " + numbers.getAverageArithmetic());
        Date finish = new Date();
        System.out.println("Work time : " + (finish.getTime()-start.getTime())/1000 + " seconds");
    }
}
