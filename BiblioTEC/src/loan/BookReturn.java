/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan;

import java.util.Date;
import materialRegistration.Book;
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class BookReturn extends Return{
    private materialRegistration.Book Book;
    
    public BookReturn(int ID, Student student, Date date,
            materialRegistration.Book _book) {
        super(ID, student, date);
        this.Book = _book;
    }

    public Book getBook() {
        return Book;
    }

    public void setBook(Book Book) {
        this.Book = Book;
    }
    
    
}
