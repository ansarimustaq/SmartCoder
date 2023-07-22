package com.example.mycart.Model;

public class Item
{
    public String getImage() {
        return image;
    }

    public String getDetails() {
        return details;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    private String image;
    private String details;

    public Item(String image, String details)
    {
        this.image = image;
        this.details = details;

    }
    public Item()
    {

    }
}
