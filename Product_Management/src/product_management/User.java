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
public class User {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    private int id;
    private String full_name;
    private String username;
    private String password;
    private String phone;
    private String gender; 
    private byte[] picture;

    public User() {
    }

    public User(int id, String full_name, String username, String password, String phone, String gender, byte[] picture) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.picture = picture;
    }
    
}
