/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import file.AudiovisualFile;
import file.BookFile;
import file.StudentFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import loan.BookLoan;
import loan.Loan;
import loan.Return;
import materialRegistration.Audiovisual;
import materialRegistration.Book;
import materialRegistration.BookType;
import studentRegistration.Career;
import studentRegistration.Student;


/**
 *
 * @author danielalvarado
 */
public class Library {

    List<List<Book>> bookList = new ArrayList<List<Book>>();
    List<Student> studentsList = new ArrayList<Student>();
    public List<Loan> registeredLoans = new ArrayList<Loan>(); 
    List<Return> returnsList = new ArrayList<Return>(); 
    private static Library libraryInstance = new Library();
    private file.LoanFile loansFile;
    List<List<Audiovisual>> audiovisualsList = new ArrayList<>();
    file.AudiovisualFile avFile;
    public List<Audiovisual> registeredAV = new ArrayList<>(); 

    
    
    
    public Library() {this.bookList = new ArrayList<>();
        registeredLoans = new ArrayList<Loan>(); 
        loansFile = new file.LoanFile(registeredLoans);
        loansFile.getData();
        
         avFile = new file.AudiovisualFile(registeredAV);
         avFile.getData();
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
    /**
     * Returns the Loan objects saved on the loansFile.dat 
     * @return A list of Loan objects
     */
    public List<Loan> getLoansFromFile(){
        if (loansFile != null){
            return loansFile.getData();
        }else{
            return null;
        }
    }
    
    public void addAudiovsToList( List<Audiovisual> audiovs) {
        this.audiovisualsList.add(audiovs);
    }
    
    public List<Audiovisual>  searchAudiovisual(String _letters, int _counter) {
        List<Audiovisual> audiovsList = audiovisualsList.get(_counter);
        List<Audiovisual> newAudiovsList = new ArrayList<>();
        Audiovisual audiovs;
        int pos = _letters.length();
        for (int i = 0; i < audiovisualsList.size();i++) {
            audiovs = audiovsList.get(i);
            String name = audiovs.getBrand();
            String part = name.substring(0, pos).toUpperCase().trim();
            String lettersUp = _letters.toUpperCase().trim();
            if (part.equals(lettersUp)&& !audiovs.onLoan()) {
                newAudiovsList.add(audiovs);
            }
            
            
        }
        return newAudiovsList;
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
            if (part.equals(lettersUp)&& !book.onLoan()) {
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
        File file = new File("./files/studentFile.dat");
        file.getParentFile().mkdirs();
        StudentFile studentFile = new StudentFile(file);
        Student newStudent = new Student(_id,_name,_lastName, _career);
        return studentFile.addEndRecord(newStudent);
    }
    
    public boolean registerStudent(Student newStudent) throws IOException {
        //File file = new File("/usr/local/bin/geeks");
        File file = new File("./files/studentFile.dat");
        file.getParentFile().mkdirs();
        StudentFile studentFile = new StudentFile(file);
        return studentFile.addEndRecord(newStudent);
    }
    
    public void loanBook(Book _book, Student _student) {
        Date actualDate = new Date();
        //int _ID, Student _student, Date _date,materialRegistration.Book _bookLoaned 
        if (!_book.onLoan()) {
            
            _book.setState(false);
            BookLoan newBookLoan = new BookLoan(_student,actualDate,_book);
            
            List<Loan> update = this.getLoansFromFile();
            if (update != null){
                update.add(newBookLoan);
                //registeredLoans = update;
                loansFile = new file.LoanFile(update);
                loansFile.saveData();
                
            }else{
                List<Loan> newLoans = new ArrayList<>();
                newLoans.add(newBookLoan);
                loansFile = new file.LoanFile(newLoans);
                loansFile.saveData();
                
            }
            
            //this.loansFile = new file.LoanFile(update);
            
            //registeredLoans.add(newBookLoan);
            //this.loansList.add(newBookLoan);
            
        }
    }

    public List<List<Book>> getBookList() {
        return bookList;
    }
    
    public int getLastLoanID(){
        try{
            if (loansFile != null){
                List<Loan> loans = loansFile.getData();
                return loans.get(loans.size()-1).getID();

            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
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
        return registeredLoans;
    }

    public void setLoansList(List<Loan> loansList) {
        this.registeredLoans = loansList;
    }

    public List<List<Audiovisual>> getAudiovisualsList() {
        return audiovisualsList;
    }

    public void setAudiovisualsList(List<List<Audiovisual>> audiovisualsList) {
        this.audiovisualsList = audiovisualsList;
    }
    
    
    public boolean deleteAudiovsList(int _pos) {
        try {this.audiovisualsList.remove(_pos);
            return true;}
        catch (Exception e) {
            System.out.print("ERROR AL BORRAR LIBRO DE LA LISTA");
            return false;
        }
    }
    
    public void deleteALLAudiovs() {
        while (!this.audiovisualsList.isEmpty()) {
            this.audiovisualsList.remove(0);
        }
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
    
        /**
     * Looks for the loans of a student
     * @param ID Student ID
     * @return A list of loans
     */
    public List<Loan> getLoansByStudentID(int ID) {
        if (loansFile != null){
            List<Loan> loans = loansFile.getData();
             
        for (int i = 0; i < registeredLoans.size(); i++){
            if (registeredLoans.get(i).getStudent().getID() == ID){
                loans.add(registeredLoans.get(i));
            }
        }
        return loans;
        }
        
        return null;
    }
    
    public List<Audiovisual> getAudiovisualsFromFile(){
        if (avFile != null){
            return avFile.getData();
        }else{
            return null;
        }
    }
    
    
    public void registerAudiovisual(materialRegistration.Audiovisual av) throws IOException {
        //escribe el libro en el archivo
        //String path = new File(".").getCanonicalPath();
        //File file = new File(path);
        /*File file = new File("./files/audiovisualFile.dat");
        file.getParentFile().mkdirs();
        AudiovisualFile avFile = new AudiovisualFile(file);*/
        //file.AudiovisualFile avFile = new file.AudiovisualFile();
        
        List<Audiovisual> update = this.getAudiovisualsFromFile();
        if (update != null){
            update.add(av);
            //registeredLoans = update;
            avFile = new file.AudiovisualFile(update);
            avFile.saveData();

        }else{
            List<Audiovisual> newLoans = new ArrayList<>();
            newLoans.add(av);
            avFile = new file.AudiovisualFile(newLoans);
            avFile.saveData();

        }
/*
        List<Audiovisual> avList = new ArrayList<>();
        //if (avList != null){
            avFile.getData();
            avList.add(av);
            avFile = new file.AudiovisualFile(avList);*/
        //}
        
        
        //crea el objeto libro para poder agregarlo en el archivo
        //Book book = new Book( _name,  _author,  _year, _type,  _isbn);
        //agrega el libro al archivo
        //return bookFile.addEndRecord(book);
        
    }
    
   
    
    
}
