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
public class AudiovisualLoan extends Loan{
    
    private materialRegistration.Audiovisual audioVisualLoaned;
    
    public AudiovisualLoan(int _ID, Student _student, Date _date, 
            materialRegistration.Audiovisual _audiovl) {
        super(_ID, _student, _date);
        this.audioVisualLoaned = _audiovl;
        
    }

    public Audiovisual getAudioVisualLoaned() {
        return audioVisualLoaned;
    }

    public void setAudioVisualLoaned(Audiovisual audioVisualLoaned) {
        this.audioVisualLoaned = audioVisualLoaned;
    }
    
    
    
}
