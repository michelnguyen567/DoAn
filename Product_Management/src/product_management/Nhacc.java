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
    private String diachincc;
    private String sdtncc;
    private byte[] imagencc;
    
    public Nhacc(int zidncc, String znamencc,String zdiachincc,String zsdtncc, byte[] zimgncc)
    {
        this.idncc = zidncc;
        this.namencc = znamencc;
        this.diachincc=zdiachincc;
        this.sdtncc=zsdtncc;
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
     public String getDiachincc()
    {
        return diachincc;
    }
   public String getSdtncc()
    {
        return sdtncc;
    }
   
    public byte[] getImagencc()
    {
        return imagencc;
    }
    
}
