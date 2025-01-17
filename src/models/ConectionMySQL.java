
package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionMySQL {
    
    private String database_name = "pharmacy";
    private String user = "root";
    private String password = "1989Iscj.";
    private String url ="jdbc:mysql://localhost:3306/" + database_name;
    Connection conn = null;
    
    public Connection getConnection(){
        try{
            //obtener valo del driver
            Class.forName("com.mysq.cj.jdbc.Driver");
            //obtener la conexion
            conn = DriverManager.getConnection(url,user,password);
        }catch(ClassNotFoundException e) {
        System.err.println("Ha ocurrido un ClassNotFoundException " + e.getMessage());
    }catch(SQLException e){
        System.err.println("Ha ocurrrido un SQLException " + e.getMessage());
    }
        return conn;
    }
    
}
