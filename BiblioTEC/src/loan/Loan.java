/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan;

import java.io.Serializable;
import java.util.Date;
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class Loan implements Serializable{
    //private int ID;
    private int ID;
    private static int idCount = 0;
    private studentRegistration.Student student;
    private Date date;
    private boolean state;

    public Loan(int _ID, Student _student, Date _date) {
        //this.setID(domain.Library.getInstance().getLastLoanID());
        this.setID(idCount++);
        this.state = true;
        this.student = _student;
        this.date = _date;
    }
    
    
    public int getID() {
        return ID;
    }
    
    

    public void setID(int ID) {
        
        this.ID = ID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Sets the state of a Loan
     * @param _state True if the loan is pending, false if it's not.
     */
    public void setState(boolean _state){
        this.state = _state;
    }
    
    
    /**
     * True is pending, false is not pending
     * @return Returns the state of a Loan.
     */
    public boolean getState(){
        return this.state;
    }
    
    
    
}
