import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

//this program will save only small images(upto 65kb)

public class ImageSave {
    public static void main(String[] args) {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "sachin", "sachin");
            String q1 = "Create table if not exists images(id int auto_increment primary key, pic blob)";
            String q2 = "Insert into images(pic) values(?)";    
            //it will take only i parameter, so parameterInted will be 1 when we will set someting 

            Statement stm = con.createStatement();
            stm.executeUpdate(q1);

            PreparedStatement pstm = con.prepareStatement(q2); 
            FileInputStream fis = new FileInputStream("sachin.jpg");
            pstm.setBinaryStream(1, fis, fis.available());  //fis.available() -> give available space
            pstm.executeUpdate();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }
    
}
