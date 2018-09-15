package main.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private int size;
    private Node head;
    private BigDecimal sum = new BigDecimal(0);
    private int max;
    private int min;
    private List<Integer> medians;

    private class Node{
        private Node left;
        private Node right;
        private int data;

        Node(int number){
            data=number;
        }
    }

    public void addData(int number){
        if(head!=null){
            addNode(head,number);
        }
        else {
            head=new Node(number);
        }
        ++size;
    }

    private int getBorder(boolean isMax){
        if(head==null){
            return 0;
        }
        int prev=head.data;
        Node cell=head;
        while(cell!=null){
            prev=cell.data;
            cell=isMax?cell.right:cell.left;
        }
        return prev;
    }

    public void addNode(Node current, int number){
        if(number>current.data){
            if(current.right!=null){
                addNode(current.right,number);
            }
            else {
                current.right=new Node(number);
            }
        }
        else {
            if(current.left!=null){
                addNode(current.left,number);
            }
            else {
                current.left=new Node(number);
            }
        }
    }

    public double getMedian(){
        if(size%2==1){
            return medians.get(0);
        }
        else {
            return (medians.get(0)+medians.get(1))/2.0;
        }
    }

    private int graphTravelling(Node current, List<Integer> numbers, int prevIndex){
        if(current==null){
            return prevIndex;
        }
        if(current==head){
            prevIndex=0;
        }
        prevIndex = graphTravelling(current.left,numbers,prevIndex);
        ++prevIndex;
        sum=sum.add(new BigDecimal(current.data));
        if(isEqualIndex(prevIndex)){
            numbers.add(current.data);
        }
        return  graphTravelling(current.right, numbers,prevIndex);
    }

    private boolean isEqualIndex(int index){
        if(size%2==1 && size/2==index){
            return true;
        }
        if(size%2==0 && (size/2==index || size/2+1==index)){
            return true;
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    public void handleData(){
        medians = new ArrayList<Integer>();
        graphTravelling(head,medians,0);
        max=getBorder(true);
        min=getBorder(false);
    }

    public int getMax(){
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getAverageArithmetic(){
        return sum.divide(new BigDecimal(size),5,BigDecimal.ROUND_UP).doubleValue();
    }
}
