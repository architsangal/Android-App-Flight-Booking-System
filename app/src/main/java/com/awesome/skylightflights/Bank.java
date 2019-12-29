package com.awesome.skylightflights;

import java.io.Serializable;

public class Bank implements Serializable
{
    public Bank() {
    }

    public Bank(String no1, String no2) {
        this.no1 = no1;
        this.no2 = no2;
    }

    private String no1,no2;

    public String getNo1() {
        return no1;
    }

    public void setNo1(String no1) {
        this.no1 = no1;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String add(String price, String price2)
    {
        return ((Integer.parseInt(price)+Integer.parseInt(price2))+"");
    }
    public String sub()
    {
        return ((Integer.parseInt(no1)-Integer.parseInt(no2))+"");
    }
}
