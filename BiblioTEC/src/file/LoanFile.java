/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.omg.CORBA.Environment;

/**
 *
 * @author CASA
 */
public class LoanFile {
    

        private FileOutputStream fos = null;
        private ObjectOutputStream salida = null;
        private List<loan.Loan> usersList = new ArrayList<>();
        //private List<domain.Event> eventsList = new ArrayList<>();


        public LoanFile() {

        }

        public LoanFile(List<loan.Loan> _userList) {
            this.usersList = _userList;
        }


        /*public LoanFile(List<domain.Event> _eventsList, int disposable) {
            this.eventsList = _eventsList;
        }*/

        public void saveData() {
            try {
                java.io.File file = new File("./files/loansFile.dat");
                //file.getParentFile().mkdir();
                if (!file.exists()){
                    file.getParentFile().mkdir(); // Will create parent directories if not exists
                    //file.createNewFile();
                }    
                    fos = new FileOutputStream(file,false);
                //fos = new FileOutputStream("users.dat");
                //fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/users.dat");
                salida = new ObjectOutputStream(fos);
                salida.writeObject(this.usersList);

                

                
            } catch (FileNotFoundException e) {
                //System.out.println("NOoooo");
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("NOoooo");
                System.err.println(e.toString());
            } finally {
                try {
                    if(fos!=null) fos.close();
                    if(salida!=null) salida.close();
                } catch (IOException e) {
                    //System.out.println("NOoooo");
                    System.err.println(e.toString());
                }
            }
        }

/*    public void saveEventData() {
        try {
            java.io.File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/events.dat");
            if (!file.exists()){
                file.getParentFile().mkdir(); // Will create parent directories if not exists
                //file.createNewFile();

            }

            fos = new FileOutputStream(file,false);

            salida = new ObjectOutputStream(fos);
            salida.writeObject(this.eventsList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(fos!=null) fos.close();
                if(salida!=null) salida.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }*/

    public List<loan.Loan>  getData() {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        List<loan.Loan> users = null;


        try {

            //fis = new FileInputStream("/ficheros/personas.dat");
            //fis = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/users.dat");
            java.io.File file = new File("./files/loansFile.dat");
            fis = new FileInputStream(file);
            entrada = new ObjectInputStream(fis);
            users = (List<loan.Loan> ) entrada.readObject(); //es necesario el casting

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
           
            System.out.println(e.getMessage());
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
        } finally {
            
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }

        return users;
    }


  /*  public List<domain.Event>  getEventsData() {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        List<domain.Event> users = null;


        try {
            java.io.File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/events.dat");
            //fos = new FileOutputStream(file,false);
            //fis = new FileInputStream("/ficheros/personas.dat");
            fis = new FileInputStream(file);
            entrada = new ObjectInputStream(fis);
            users = (List<domain.Event> ) entrada.readObject(); //es necesario el casting

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return users;
    }*/



    
}
