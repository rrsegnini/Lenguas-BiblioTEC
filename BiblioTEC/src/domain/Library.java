/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import materialRegistration.Book;


/**
 *
 * @author danielalvarado
 */
public class Library {
    List<List<Book>> bookList;
    
    public Library() {this.bookList = new ArrayList<>();
}
    
    public void searchBook(String _letters, int _counter) {
        List<Book> books = bookList.get(_counter);
        Book book;
        int pos = _letters.length();
        for (int i = 0; i < books.size();i++) {
            book = books.get(i);
            if (book.getName().substring(0, pos) == _letters) {
                books.add(book);
            }
            
        }
    }
    
}
