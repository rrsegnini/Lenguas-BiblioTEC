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
import studentRegistration.Student;

/**
 *
 * @author danielalvarado
 */
public class StudentFile {
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//me indica la cantidad de registros
    private int regSize;
    private String myFilePath;
    
    
    //constructor
    /**
     * 
     * @param file
     * @throws IOException
     */
    public StudentFile(File file) throws IOException{
        start(file);
    }
    
    private void start(File file) throws IOException{
        //almaceno la ruta
        myFilePath = file.getPath();
        
        //tamanno maximo de los registros dentro de esta 
        //clase
        this.regSize = 100;
        
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
    
    /**
     * inserta un nuevo registro pero en una posicion existente
     * @param position
     * @param _student
     * @return
     * @throws IOException
     */
    public boolean putValue(int position, Student _student) throws IOException{
        //una pequenna validacion antes de insertar
        if(position >= 0 && position <= regsQuantity){
            if(_student.size() > regSize){
                System.err.print("7001 record size is out of bounds");
                return false;
            }
            else{
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(_student.getName());
                randomAccessFile.writeUTF(_student.getLastName());
                randomAccessFile.writeInt(_student.getID());
                
                return true;
            }
        }
        else{
            System.err.print("7002 position is "
                    + "out of bounds of this file");
                return false;
        }
        
    }//fin metodo
    
    /**
     * agrega un registro nuevo pero al final del archivo, por esa razon
     * se incrementa la cantidad de registros
     * @param person
     * @return
     * @throws IOException
     */
    public boolean addEndRecord(Student _student) throws IOException{
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, _student);
        
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
    public Student getStudent(int position) throws IOException{
        //validacion de la posicion
        if(position >= 0 && position <= regsQuantity){
            //colocamos el puntero en el lugar 
            randomAccessFile.seek(position * regSize);
            
            //instancia de person
            Student myStudent = new Student();
            
            //llevamos a cabo las lecturas
            myStudent.setID(randomAccessFile.readInt());
            myStudent.setName(randomAccessFile.readUTF());
            myStudent.setLastName(randomAccessFile.readUTF());
            myStudent.setCareerStr(randomAccessFile.readUTF());
            
            //si es delete no retorno
            if(myStudent.getName().equalsIgnoreCase("delete")){
                return null;
            }
            else{
                return myStudent;
            }
            
        }
        else{
            System.err.println("6001 position is out of bounds");
            return null;
        }
    }//fin de metodo
    
    public Student getStudentByName(String _name) throws IOException {
        Student myStudent;
        
        //buscar el registro para la eliminacion
        for(int i = 0; i < regsQuantity; i++){
            
            //obtengo a la persona de esa posicion
            myStudent = this.getStudent(i);
                
            if(myStudent.getName().equalsIgnoreCase(_name)){

                return myStudent;
            }
        }
        
        return null;
    }
    

    /**
     * consulta todos los registros de mi archivo
     * @return una lista de objetos tipo Person
     * @throws IOException
     */
    public List<Student> getAllStudents() throws IOException{
        
        //variables a retornar
        List<Student> students = new ArrayList<Student>();
        
        //recorro todos mis registros y los inserto en la lista
        for(int i = 0; i < regsQuantity; i++){
            Student studentTemp = this.getStudent(i);
            
            if(studentTemp != null){
                students.add(studentTemp);
            }
        }
        
        return students;
    }//fin metodo
    
    
    public boolean deleteRecord(String name) throws IOException{
        Student myStudent;
        
        //buscar el registro para la eliminacion
        for(int i = 0; i < regsQuantity; i++){
            
            //obtengo a la persona de esa posicion
            myStudent = this.getStudent(i);
                
            //pregunto si es la persona que quiero eliminar
            if(myStudent.getName().equalsIgnoreCase(name)){

                //marcar esta persona como eliminada
                myStudent.setName("deleted");

                return this.putValue(i, myStudent);
            }
        }
        
        //si llega a este punto no encontro a la persona
        return false;
    }
       
    
}
