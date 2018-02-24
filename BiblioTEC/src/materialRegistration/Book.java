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
    
    public Book() {
        
    }
    
    public Book(String _name, String _author, int _year, BookType _type, String _isbn) {
        this.id++;
        this.name = _name;
        this.author = _author;
        this.year = _year;
        this.type = _type;
        this.ISBN = _isbn;
        this.state = false;
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
