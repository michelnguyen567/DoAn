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
public class Category {
    
    private int cateid;
    private String name;
    private String description;

    public Category() {
    }

    public Category(int cateid, String name, String description) {
        this.cateid = cateid;
        this.name = name;
        this.description = description;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
