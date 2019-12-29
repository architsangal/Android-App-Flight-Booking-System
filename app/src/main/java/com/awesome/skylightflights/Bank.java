package com.awesome.skylightflights;

import java.io.Serializable;

public class Bank implements Serializable
{
    public String add(String price,String price2)
    {
        return ((Integer.parseInt(price)+Integer.parseInt(price2))+"");
    }
    public String sub(String price,String price2)
    {
        return ((Integer.parseInt(price)-Integer.parseInt(price2))+"");
    }
}
