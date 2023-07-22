package com.example.mycart.Model;

public class ItemDetail
{
    String name;
    String url;
    int price;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }



    public ItemDetail()
    {

    }

    public ItemDetail(String name, String url, int price)
    {
        this.name = name;
        this.url = url;
        this.price = price;
    }

}
