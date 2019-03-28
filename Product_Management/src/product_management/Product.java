/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product_management;

/**
 *
 * @author kazuto
 */
public class Product {
    
    private int id;
    private int cateid;
    private String name;
    private float price;
    private String addDate; 
    private byte[] picture;
    
    public Product(int pid, int pcateid, String pname, float pprice, String pAddDate, byte[] pimg)
    {
        this.id = pid;
        this.cateid = pcateid;
        this.name = pname;
        this.price = pprice;
        this.addDate = pAddDate;
        this.picture = pimg;
    }
    
    public int getId()
    {
        return id;
    }
    
     public int getCateID()
    {
        return cateid;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getPrice()
    {
        return price;
    }
    
    public String getAddDate()
    {
        return  addDate;
    }
    
    public byte[] getImage()
    {
        return picture;
    }
    
}