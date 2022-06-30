package com.jdbc.practice;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    private static Connection con;
    
    public static Connection getConnection(){
        try{
            if(con==null){
                //to use the jdbc driver jar file, right click on Libraries -> Add jar -> then 
                // choose the jar file.
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "sachin", "sachin");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
                
        return con;
    }
    
}
