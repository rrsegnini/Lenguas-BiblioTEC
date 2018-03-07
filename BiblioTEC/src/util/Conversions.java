/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import file.BookFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import materialRegistration.Book;
import studentRegistration.Career;
import view.loan.BookLoanForm;
/**
 *
 * @author danielalvarado
 */
public class Conversions {
    public Conversions() {}
    
    public static String career2str(Career _career) {
        String career = "";
        switch (_career) {
            case COMPUTER: 
                career = "COMPUTER";
                break;
            case MECHATRONICS:
                career = "MECHATRONICS";
                break;
            case ELECTRONIC:
                career = "ELECTRONIC";
                break;
                
        }   
        return career;
        
    }
    
    public static Career str2career(String _strCareer) {
        Career career = Career.COMPUTER;
        switch (_strCareer) {
            case "COMPUTER": 
                career = Career.COMPUTER;
                break;
            case "MECHATRONICS":
                career = Career.MECHATRONICS;
                break;
            case "ELECTRONIC":
                career = Career.ELECTRONIC;
                break;
                
        }   
        return career;
    
    }
    
    public static int getNewID() {
        File file = new File("./files/bookFile.dat");
        int id = 1;
            try {
                BookFile bookFile = new BookFile(file);
                id = bookFile.createNewID();
            } catch (IOException ex) {
                Logger.getLogger(BookLoanForm.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                return id;
            }
    
    }
}
