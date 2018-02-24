/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentRegistration;

/**
 *
 * @author danielalvarado
 */
public class Student {
    private int ID;
    private String name;
    private String lastName;
    private Career career;
    
    
    public Student(int _id, String _name, String _lastName, Career _career) {
        this.ID = _id;
        this.name = _name;
        this.lastName = _lastName;
        this.career = _career;
    }

    public int getID() {
        return ID;
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
    
    /**
     * metodo que me retorna el tamanno en bytes del objeto
     * actual
     * @return
     */
    public int size() {
        return this.getFullName().length()*2 + this.getLastName().length()*2
                +4;
    }
    
    
}
