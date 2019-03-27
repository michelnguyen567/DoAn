/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product_management;

/**
 *
 * @author PHUC
 */
public class Nhacc {
     private int idncc;
    private String namencc;

    private byte[] imagencc;
    
    public Nhacc(int zidncc, String znamencc, byte[] zimgncc)
    {
        this.idncc = zidncc;
        this.namencc = znamencc;
        
        this.imagencc = zimgncc;
    }
    
    public int getIdncc()
    {
        return idncc;
    }
    
    public String getNamencc()
    {
        return namencc;
    }
    
  
   
    public byte[] getImagencc()
    {
        return imagencc;
    }
    
}
