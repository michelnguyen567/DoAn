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
public class Shop {

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Shop(int shopid, String name, String address, String phone, String addDate, byte[] picture) {
        this.shopid = shopid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.addDate = addDate;
        this.picture = picture;
    }

    public Shop() {
    }
    
    private int shopid;
    private String name;
    private String address;
    private String phone;
    private String addDate;
    private byte[] picture;
    
}
