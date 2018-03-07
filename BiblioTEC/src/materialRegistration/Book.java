  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materialRegistration;


import util.Conversions;
import java.io.Serializable;


/**
 *
 * @author danielalvarado
 */
public class Book implements Material, Serializable{
    private int id;
    private String name;
    private String author;
    private int year;
    private BookType type;
    private String ISBN;
    private boolean state;
    
    public Book() {
        
    }
    
    public Book(String _name, String _author, int _year, BookType _type, String _isbn) {
        this.id = Conversions.getNewID();
        this.name = _name;
        this.author = _author;
        this.year = _year;
        this.type = _type;
        this.ISBN = _isbn;
        this.state = false;
    }
    
    public int size() {
        return 4+name.length()*2 + author.length() + 4 + 12*2 + ISBN.length() 
                +1;
    }
    
    //
    
    @Override
    public int getID() {return this.id;}
    
    public String getName() {return this.name;}
    public void setName(String _name) {this.name = _name;}
    
    public String getAuthor() {return this.author;}
    public void setAuthoer(String _author) {this.author = _author;}
    
    public int getYear() {return this.year;}
    public void setYear(int _year) {this.year = _year;}
    
    public String getBookTypeStr() {
        return BookType2Str(this.type);
    }
    public void setType(BookType _type) {this.type = _type;}
    
    public String getISBN() {return this.ISBN;}
    public void setISBN(String _isbn) {this.ISBN = _isbn;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * If a book is on a loan or not
     * @return True if the book is loaned, false if it isn't.
     */
    public boolean getState() {
        return state;
    }
    /**
     * Sets a book as available or not (true is available, false is unavailable)
     * @param state 
     */
    public void setState(boolean state) {
        this.state = state;
    }
    
    
    
    private String BookType2Str(BookType _type) {
        if (_type == BookType.DIGITAL) {
            return "Digital";
        }
        else {
            return "Physical";
        }
    }
    
    @Override
    public boolean onLoan() {return this.state;};
    
    @Override
    public void lendingMaterial() {this.state = true;}
}
