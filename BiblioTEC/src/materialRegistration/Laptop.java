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
public class Laptop extends Audiovisual{
    private int screenSize;
    private LaptopOS os;
    
    public Laptop(String _model, String _brand, int _serialNumber, int _screenSize,
            LaptopOS _os) {
    super(_model,_brand,_serialNumber);
    this.screenSize = _screenSize;
    this.os = _os;
    }
    
    public int getScreenSize() {
        return this.screenSize;
    }
    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
    public LaptopOS getOs() {
        return os;
    }
    public void setOs(LaptopOS os) {
        this.os = os;
    }
   
}
