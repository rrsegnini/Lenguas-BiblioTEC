/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materialRegistration;

/**
 *
 * @author danielalvarado
 */
public class Audiovisual implements Material{
    private int id;
    private boolean status;
    private String model;
    private String brand;
    private int serialNumber;
    
    public boolean onLoan() {return true;}
}
