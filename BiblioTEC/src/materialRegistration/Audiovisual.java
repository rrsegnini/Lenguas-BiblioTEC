/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materialRegistration;

import file.AudiovisualFile;
import java.io.Serializable;

/**
 *
 * @author danielalvarado
 */
public class Audiovisual implements Material, Serializable{
    private int id;
    private boolean status;
    private String model;
    private String brand;
    private int serialNumber;
    private boolean state;
    
    public Audiovisual() {
    }
    public Audiovisual(String _model, String _brand, int _serialNumber) {
        AudiovisualFile avf = new AudiovisualFile();
        id = avf.createNewID();
        this.status = false;
        this.serialNumber =_serialNumber;
        this.model = _model;
        this.brand = _brand;
    }
    
    @Override
    public int getID() {return this.id;}
    @Override
    public boolean onLoan() {return this.status;}
    @Override
    public void lendingMaterial() {this.status = true;}
    
    public String getModel() {return this.model;}
    public void setModel(String _model) {this.model = _model;}
   
    public String getBrand() {return this.brand;}
    public void setBrand(String _brand) {this.brand = _brand;}
    
    public int getSerialNumber() {return this.serialNumber;}
    public void setSerialNumber(int _serialNumber) {this.serialNumber = _serialNumber;}
    
     /**
     * If an audiovisual is on a loan or not
     * @return True if the book is loaned, false if it isn't.
     */
    public boolean getState() {
        return state;
    }
    /**
     * Sets an audiovisual as available or not (true is available, false is unavailable)
     * @param state 
     */
    public void setState(boolean state) {
        this.state = state;
    }
    
    
    
    
    
}
