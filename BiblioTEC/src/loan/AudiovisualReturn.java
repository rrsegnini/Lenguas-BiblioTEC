/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan;

import java.util.Date;
import materialRegistration.Audiovisual;
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class AudiovisualReturn extends Return{
    private materialRegistration.Audiovisual  audioVisual;

    public Audiovisual getAudioVisual() {
        return audioVisual;
    }

    public void setAudioVisual(Audiovisual audioVisual) {
        this.audioVisual = audioVisual;
    }

    public AudiovisualReturn(int ID, Student student, Date date, materialRegistration.Audiovisual _av) {
        super(ID, student, date);
        this.audioVisual = _av;
    }
    
}
