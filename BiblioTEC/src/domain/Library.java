/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import file.BookFile;
import file.StudentFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import loan.BookLoan;
import loan.Loan;
import materialRegistration.Book;
import materialRegistration.BookType;
import studentRegistration.Career;
import studentRegistration.Student;


/**
 *
 * @author danielalvarado
 */
public class Library {
    List<List<Book>> bookList = new ArrayList<>();
    List<Student> studentsList = new ArrayList<>();
    List<Loan> loansList = new ArrayList<>(); 
    private static Library libraryInstance;
    
    
    
    public Library() {this.bookList = new ArrayList<>();
    }
    
    public static Library getInstance(){
        if (libraryInstance == null){
            return new Library();
        }else{
            return libraryInstance;
        }
    }
    
    public void addBooksToList(List<Book> _books) {
        this.bookList.add(_books);
    }
    
    public List<Book>  searchBook(String _letters, int _counter) {
        List<Book> books = bookList.get(_counter);
        List<Book> newBookList = new ArrayList<>();
        Book book;
        int pos = _letters.length();
        for (int i = 0; i < books.size();i++) {
            book = books.get(i);
            String name = book.getName();
            String part = name.substring(0, pos).toUpperCase().trim();
            String lettersUp = _letters.toUpperCase().trim();
            if (part.equals(lettersUp)) {
                newBookList.add(book);
            }
            
            
        }
        return newBookList;
    }
    
    public boolean registerBook(String _name, String _author, int _year, 
            BookType _type, String _isbn) throws IOException {
        //escribe el libro en el archivo
        //String path = new File(".").getCanonicalPath();
        //File file = new File(path);
        File file = new File("./files/bookFile.dat");
        file.getParentFile().mkdirs();
        BookFile bookFile = new BookFile(file);
        //crea el objeto libro para poder agregarlo en el archivo
        Book book = new Book( _name,  _author,  _year, _type,  _isbn);
        //agrega el libro al archivo
        return bookFile.addEndRecord(book);
        
    }
    
    public boolean registerStudent(int _id, String _name, String _lastName, Career _career) throws IOException {
        //File file = new File("/usr/local/bin/geeks");
        File file = new File("./files/bookFile.dat");
        file.getParentFile().mkdirs();
        StudentFile studentFile = new StudentFile(file);
        Student newStudent = new Student(_id,_name,_lastName, _career);
        return studentFile.addEndRecord(newStudent);
    }
    
    public boolean registerStudent(Student newStudent) throws IOException {
        //File file = new File("/usr/local/bin/geeks");
        File file = new File("./files/bookFile.dat");
        file.getParentFile().mkdirs();
        StudentFile studentFile = new StudentFile(file);
        return studentFile.addEndRecord(newStudent);
    }
    
    public void loanBook(Book _book, Student _student) {
        Date actualDate = new Date();
        //int _ID, Student _student, Date _date,materialRegistration.Book _bookLoaned 
        if (!_book.onLoan()) {
            BookLoan newBookLoan = new BookLoan(1,_student,actualDate,_book);
        }
    }

    public List<List<Book>> getBookList() {
        return bookList;
    }

    public void setBookList(List<List<Book>> bookList) {
        this.bookList = bookList;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public List<Loan> getLoansList() {
        return loansList;
    }

    public void setLoansList(List<Loan> loansList) {
        this.loansList = loansList;
    }
    
    public boolean deleteBooksList(int _pos) {
        try {this.bookList.remove(_pos);
            return true;}
        catch (Exception e) {
            System.out.print("ERROR AL BORRAR LIBRO DE LA LISTA");
            return false;
        }
    }
    
    public void deleteALLBooks() {
        while (!this.bookList.isEmpty()) {
            this.bookList.remove(0);
        }
    }
    
}
