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
public class BookLoan extends Loan{
    
    private materialRegistration.Book bookLoaned;
    
    public BookLoan(int _ID, Student _student, Date _date,
            materialRegistration.Book _bookLoaned ) {
        super(_ID, _student, _date);
        this.bookLoaned = _bookLoaned;
    }

    public Book getBookLoaned() {
        return bookLoaned;
    }

    public void setBookLoaned(Book bookLoaned) {
        this.bookLoaned = bookLoaned;
    }
    
    
}
