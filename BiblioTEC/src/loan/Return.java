/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan;

import java.util.Date;
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class Return {
    private int ID;
    private studentRegistration.Student student;
    private Date date;

    public Return(int ID, Student student, Date date) {
        this.ID = ID;
        this.student = student;
        this.date = date;
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
    
    
}
