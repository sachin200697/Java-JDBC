import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

// import javax.sql.DataSource;
// import java.sql.Statement;
// import java.sql.SQLException;

public class MysqlJDBC{
    public static void main(String[] args) {
        Connection con = null;
        try {
            // 1. load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //2. createing connection
            String url = "jdbc:mysql://localhost:3306/practice";
            String user = "sachin";
            String password = "sachin";
            con = DriverManager.getConnection(url, user, password);

            //check if connection is open or not
            if(con.isClosed()){
                System.out.println("Due to some error connection is not open!!");
            }else{
                System.out.println("Connection has stablished...");
            }

            //3. creating query
            String s1 = "Create table if not exists user(id int(5) auto_increment primary key, name varchar(20) not null)";
            String s2 = "delete from user";
            String s3 = "insert into user(name) values(\"Sachin\")";
            String s4 = "insert into user(name) values(\"Pradeep\")";
            String s5 = "Select * from user";            

            Statement stmt = con.createStatement();
            
            stmt.executeUpdate(s1);
            stmt.executeUpdate(s2);
            stmt.executeUpdate(s3);
            stmt.executeUpdate(s4);

            // PreparedStatement query
            String s6 = "insert into user(id, name) values(?, ?)";

            PreparedStatement pstmt = con.prepareStatement(s6);
            pstmt.setInt(1, 10);
            pstmt.setString(2, "Arun");
            pstmt.executeUpdate();

            ResultSet set = stmt.executeQuery(s5);

            // 4. process the data
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                System.out.println(id + " " + name);
            }

            

            
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        
        }
        finally{
            try {
                //need to close it inside try and catch block otherwise it will give exception
                con.close();
            }catch(Exception e){

            }
        }
        
        
    }
}