package com.jdbc.practice;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        try{
            con = ConnectionProvider.getConnection();
            String q1 = "create table if not exists LargeImages(id int auto_increment primary key, "
                    + "pic longblob)";
            
            String q2 = "insert into LargeImages(pic) values(?)";
            
            Statement stm = con.createStatement();
            stm.executeUpdate(q1);
            
            PreparedStatement pstm = con.prepareStatement(q2);
            
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);
            
            File f = jfc.getSelectedFile();
            
            FileInputStream fis = new FileInputStream(f);
            pstm.setBinaryStream(1, fis, fis.available());
            
            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Success");
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                con.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }      
    }
    
}
