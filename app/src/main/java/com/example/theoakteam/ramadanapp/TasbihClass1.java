package com.example.theoakteam.ramadanapp;

import java.util.HashMap;

/**
 * Created by Sunny-PC on 5/24/2015.
 */
public class TasbihClass1 {

    private Integer counter;
    private boolean flag;

    public Integer tasbihCounter(){
        counter++;



        return  counter;
    }

    public TasbihClass1() {

        setCounter(0);
        setFlag(true);

    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
