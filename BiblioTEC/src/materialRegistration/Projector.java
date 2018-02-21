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
public class Projector extends Audiovisual{
    private int lumens;
    
    public Projector(String _model, String _brand, int _serialNumber, int _lumens) {
        super(_model,_brand,_serialNumber); 
        this.lumens = _lumens;
    }

    public void setLumens(int lumens) {
        this.lumens = lumens;
    }
    public int getLumens() {
        return lumens;
    }

}
