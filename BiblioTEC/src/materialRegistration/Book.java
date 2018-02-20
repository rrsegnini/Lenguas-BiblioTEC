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
public class Book implements Material{
    private int id;
    private String name;
    private String author;
    private int year;
    private BookType type;
    private String ISBN;
    private boolean state;
    public Book() {}
    
    @Override
    public boolean onLoan() {return true;};
}
