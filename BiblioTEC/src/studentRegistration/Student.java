/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentRegistration;

import java.util.Collections;

/**
 *
 * @author danielalvarado
 */
public class Student {
    private int ID;
    private String name;
    private String lastName;
    private Career career;
    
    
    public Student() {
        ID = 0;
        name = "";
        lastName = "";
        career = Career.COMPUTER;
        
    }
    
    public Student(int _id, String _name, String _lastName, Career _career) {
        this.ID = _id;
        this.name = _name;
        this.lastName = _lastName;
        this.career = _career;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCareer(Career career) {
        this.career = career;
    }
    
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return this.name + " " + this.lastName;
    }

    public String getCareerStr() {
        return util.Conversions.career2str(this.career);
    }
    
    public void setCareerStr(String _career) {
        this.career = util.Conversions.str2career(_career);
    }
    
    @Override
    public String toString() {
        return "Student{" + "ID=" + ID + ", name=" + name + ", lastName=" + lastName + ", career=" + this.getCareerStr() + '}';
    }
    
    
    /**
     * metodo que me retorna el tamanno en bytes del objeto
     * actual
     * @return
     */
    public int size() {
        return this.getFullName().length()*2 + this.getLastName().length()*2
                +4+12*2; //12 es la longitud maxima del nombre de la carrera
    }
    
    
}
