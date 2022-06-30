import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class DynamicUpdataeMysqlJDBC {
    public static void main(String[] args) {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // "jdbc:mysql://localhost:3306/practice";
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "sachin", "sachin");

            if(con.isClosed()){
                System.out.println("Connection is still closed");
            }else{
                System.out.println("Connection stablished....");
            }
            String q1 = "insert into user(id, name) values(?,?)";

            PreparedStatement pstmt = con.prepareStatement(q1);

            int id;
            String name;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter new user id:");
            id = Integer.parseInt(br.readLine());

            System.out.println("Enter new user name:");
            name = br.readLine();

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }finally{
            try {
                con.close();
            } catch (Exception ex) {
                //TODO: handle exception
            }
        }

    }
    
}
