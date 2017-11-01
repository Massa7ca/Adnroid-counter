package com.example.kairat.counter;

public class Pair<Left, Right> {

    private Left left;
    private Right right;

    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    public Pair() {
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString(){
        return String.valueOf(left) + ", " + String.valueOf(right);
    }

    public void setLeft(Left left) {
        this.left = left;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public void set(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    public Pair<Left, Right> clone() { return new Pair<Left, Right>(this.left, this.right); }

    public Left getLeft() {
        return left;
    }

    public Right getRight() {
        return right;
    }

}

