/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import materialRegistration.Book;
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class BookFile {
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//me indica la cantidad de registros
    private int regSize;
    private String myFilePath;
    
    
    public BookFile(File file) throws IOException{
        start(file);
    }

    private void start(File file) throws IOException  {
            //almaceno la ruta
        myFilePath = file.getPath();
        
        //tamanno maximo de los registros dentro de esta 
        //clase
        this.regSize = 140;
        
        //una validacion basica
        if(file.exists() && !file.isFile()){
            throw new IOException(file.getName() 
                    + " is an invalid file");
        }
        else{
            //crear la nueva instancia de randomAccessFile
            randomAccessFile = new RandomAccessFile(file, "rw");
            
            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = (int)Math.ceil((double)randomAccessFile.length() / (double)regSize);
        }
    }
    
    public int fileSize(){
        return regsQuantity;
    }
    
    public boolean putValue(int position, Book _book) throws IOException{
        //una pequenna validacion antes de insertar
        if(position >= 0 && position <= regsQuantity){
            if(_book.size() > regSize){
                System.err.print("7001 record size is out of bounds");
                return false;
            }
            else{
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeInt(_book.getID());
                randomAccessFile.writeUTF(_book.getName());
                randomAccessFile.writeUTF(_book.getAuthor());
                randomAccessFile.writeInt(_book.getYear());
                randomAccessFile.writeUTF(_book.getISBN());
                randomAccessFile.writeBoolean(_book.onLoan());
                return true;
            }
        }
        else{
            System.err.print("7002 position is "
                    + "out of bounds of this file");
                return false;
        }
        
    }
    
    
    

     public List<Book> getBooks(String _letters) throws IOException {
        
        Book book;
        List<Book> books = new ArrayList<Book>();
        int pos = _letters.length();
        for(int i = 0; i < regsQuantity; i++){
            
            //obtengo a la persona de esa posicion
            book = this.getBook(i);
            String name = book.getName();
            if (name.substring(0, pos) == _letters) {
                books.add(book);
            }
        }
        return books;
    }
     
    
    /**
     * agrega un registro nuevo pero al final del archivo, por esa razon
     * se incrementa la cantidad de registros
     * @param person
     * @return
     * @throws IOException
     */
    public boolean addEndRecord(Book _book) throws IOException{
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, _book);
        
        if(success){
            ++regsQuantity;
        }
        return success;
    }
    
    
    /**
     * obtiene un registro de una persona en la posicion indicada
     * @param position
     * @return objeto de tipo Person con los datos del registro de esa persona
     * @throws IOException
     */
    public Book getBook(int position) throws IOException{
        //validacion de la posicion
        if(position >= 0 && position <= regsQuantity){
            //colocamos el puntero en el lugar 
            randomAccessFile.seek(position * regSize);
            
            //instancia de person
            Book book = new Book();
            
            //llevamos a cabo las lecturas
            book.setId(randomAccessFile.readInt());
            book.setName(randomAccessFile.readUTF());
            book.setAuthoer(randomAccessFile.readUTF());
            book.setYear(randomAccessFile.readInt());
            book.setISBN(randomAccessFile.readUTF());
            book.setState(randomAccessFile.readBoolean());
            
            
            //si es delete no retorno
            if(book.getName().equalsIgnoreCase("delete")){
                return null;
            }
            else{
                return book;
            }
            
        }
        else{
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }//fin de metodo
    
    public Book getBooktByName(String _name) throws IOException {
        Book book;
        
        //buscar el registro para la eliminacion
        for(int i = 0; i < regsQuantity; i++){
            
            //obtengo a la persona de esa posicion
            book = this.getBook(i);
                
            if(book.getName().equalsIgnoreCase(_name)){

                return book;
            }
        }
        
        return null;
    }
    
    
    
    
    /**
     * consulta todos los registros de mi archivo
     * @return una lista de objetos tipo Person
     * @throws IOException
     */
    public List<Book> getAllBooks() throws IOException{
        
        //variables a retornar
        List<Book> books = new ArrayList<Book>();
        
        //recorro todos mis registros y los inserto en la lista
        for(int i = 0; i < regsQuantity; i++){
            Book bookTemp = this.getBook(i);
            
            if(bookTemp != null){
                books.add(bookTemp);
            }
        }
        
        return books;
    }//fin metodo
    
    
    public boolean deleteRecord(String name) throws IOException{
        Book book;
        
        //buscar el registro para la eliminacion
        for(int i = 0; i < regsQuantity; i++){
            
            //obtengo a la persona de esa posicion
            book = this.getBook(i);
                
            //pregunto si es la persona que quiero eliminar
            if(book.getName().equalsIgnoreCase(name)){

                //marcar esta persona como eliminada
                book.setName("deleted");

                return this.putValue(i, book);
            }
        }
        
        //si llega a este punto no encontro a la persona
        return false;
    }
    
}
