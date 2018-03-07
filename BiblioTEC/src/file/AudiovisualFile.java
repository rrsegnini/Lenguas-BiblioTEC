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
import materialRegistration.Audiovisual;
import org.omg.CORBA.Environment;

/**
 *
 * @author danielalvarado
 */
public class AudiovisualFile {

        private FileOutputStream fos = null;
        private ObjectOutputStream salida = null;
        private List<Audiovisual> audioVlList = new ArrayList<>();


        public AudiovisualFile() {

        }



        public void saveData() {
            try {
                java.io.File file = new File("./files/audiovisualFile.dat");
                if (!file.exists()){
                    file.getParentFile().mkdir(); // Will create parent directories if not exists
                    //file.createNewFile();

                }

                fos = new FileOutputStream(file,false);
                //fos = new FileOutputStream("users.dat");
                //fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/users.dat");
                salida = new ObjectOutputStream(fos);
                salida.writeObject(this.audioVlList);
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
        }


    public List<Audiovisual>  getData() {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        List<Audiovisual> audiovisuals = null;


        try {

            //fis = new FileInputStream("/ficheros/personas.dat");
            //fis = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/users.dat");
            java.io.File file = new File("./files/audiovisualFile.dat");
            fis = new FileInputStream(file);
            entrada = new ObjectInputStream(fis);
            audiovisuals = (List<Audiovisual> ) entrada.readObject(); //es necesario el casting

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

        return audiovisuals;
    }

}