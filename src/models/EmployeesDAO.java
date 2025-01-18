
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

//Aqui van los metodos que interactuan con mysql
public class EmployeesDAO {
    //Instaciomaos la conexion
    ConectionMySQL cn = new ConectionMySQL();
    //Estas variables sirve para conectarnos a la base de datos
    Connection conn;
    PreparedStatement pst;  //sirve para las consuktas
    ResultSet rs;  //sirve para obtener datos de la consulta
    
    //Variables que nos permitiran datos entre interfaces
    public static int id_user = 0;
    public static String full_name_user = "";
    public static String username_user = "";
    public static String address_user = "";
    public static String telephone_user = "";
    public static String email_user = "";
    public static String rol_user = "";


    
    
    //Metodo de login
    public Employees loginQuery(String user, String password){
        //Creamos nuestra query que consultara en la db
        String query = "SELECT * FROM employees WHERE username = ? AND password = ?";
        //Creamos un empleado
        Employees employee = new Employees();
        //creamos un try catch ya que vamos a ingresar a la base de datos mpara capturar lo errores
        try{
            //llamamos a la conexion y le vamos a pasar la consulta
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //Enviar parametros de la consulta
            pst.setString(1, user);
            pst.setString(2, password);
            //ejecutamos la consulta
            rs = pst.executeQuery();
            
            //validamos si el user name y password coinciden, para acceder a los metodos getter y setter del empleado
            if(rs.next()){
                employee.setId(rs.getInt("id"));  //vamos a almacenar lo que tare el resultado de la consulta
                id_user = employee.getId();  //le pasamos al id_user el id que viene del usuario para que coincida
                employee.setFull_name(rs.getString("full_name")); //almacenamos el nombre del empleado
                full_name_user = employee.getFull_name();
                employee.setUsername(rs.getString("username"));
                username_user = employee.getUsername();
                employee.setAddress(rs.getString("address"));
                address_user = employee.getAddress();
                employee.setTelephone(rs.getString("telephone"));
                telephone_user = employee.getTelephone();
                employee.setEmail(rs.getString("email"));
                email_user = employee.getEmail();
                employee.setRol(rs.getString("rol"));
                rol_user = employee.getRol();
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener al empleado " + e);
        }
        
        return employee;
    }
    
    //registrar Empleado
    public boolean resgiterEmployeeQuery(Employees employee){
        //Creamos nuestra query que le vamos a pasar para agregar un empleado nuevo
        String query = "INSERT INTO employees(id, full_name,username, address, telephone, email, password, rol, created, update) VALUES(?,?,?,?,?,?,?,?,?,?)";
        //esta variable se utilizara para el create y el update
        Timestamp datetime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //accedemos a los setter del empleado para registrar los datos
            pst.setInt(1, employee.getId());
            pst.setString(2, employee.getFull_name());
            pst.setString(3, employee.getUsername());
            pst.setString(4, employee.getAddress());
            pst.setString(5, employee.getTelephone());
            pst.setString(6, employee.getEmail());
            pst.setString(7,employee.getPassword());
            pst.setString(8, employee.getRol());
            pst.setTimestamp(9, datetime);
            pst.setTimestamp(10, datetime);
            
            //ejecutamos la sentencia sql
            pst.execute();
            
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al crear el empleado " + e);
            return false;
        }
    }
    
    
    //Metodo para listar empleados
    public List listEmployeesQuery(String value){
        List<Employees> list_employees = new ArrayList();
        
        //creaos nuestra query
        String query = "SELECT * FROM employees ORDER BY rol ASC";
        //query para buscar un empleado
        String query_search_employee = "SELECT * FROM employees WHERE id LIKE '%" + value + "%'";
        
        try{
            conn = cn.getConnection();
            
            //si la persona no ingresa nada en el cuadro de busqueda se ejcuta el if
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {  //si la persona ingresa un dato en la busqueda se ejecuta el 2 query
                pst = conn.prepareStatement(query_search_employee);
                rs = pst.executeQuery();
            }
            
            //si encuentra registros recoirre el la lista cuando se consulta
            while(rs.next()){
                
                Employees employee = new Employees();
                employee.setId(rs.getInt("id"));
                employee.setFull_name(rs.getString("full_name"));
                employee.setUsername(rs.getString("username"));
                employee.setAddress(rs.getString("address"));
                employee.setTelephone(rs.getString("telephone"));
                employee.setEmail(rs.getString("email"));
                employee.setRol(rs.getString("rol"));
                
                list_employees.add(employee);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,  e.toString());
        }
        
        return list_employees;
    }
    
    
    //Modificar empleado
    public boolean updateEmployeeQuery(Employees employee){
        //Creamos nuestra query que le vamos a pasar para agregar un empleado nuevo
        String query = "UPDATE employees SET full_name = ? ,username = ?, address= ?, telephone= ?, email = ?, rol = ?, update = ? WHERE id = ?";
        //esta variable se utilizara para el create y el update
        Timestamp datetime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //accedemos a los setter del empleado para registrar los datos
            pst.setString(1, employee.getFull_name());
            pst.setString(2, employee.getUsername());
            pst.setString(3, employee.getAddress());
            pst.setString(4, employee.getTelephone());
            pst.setString(5, employee.getEmail());
            pst.setString(6, employee.getRol());
            pst.setTimestamp(7, datetime);
            pst.setInt(8, employee.getId());
            
            //ejecutamos la sentencia sql
            pst.execute();
            
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el empleado " + e);
            return false;
        }
    }
    
    //Eliminar empleado
    public boolean deleteEmployeeQuery(int id){
        String query = "DELETE FROM employees WHERE ID = " + id;
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar un empleado que tenga relacion con otra tabla ");
            return false;
        }
        }
    
    //Cambiar la contraseña solo si el usuario se encuentra logeado
    public boolean updateEmployeePassword(Employees employee){
        String query = "UPDATE employees SET password = ? WHERE username = '" + username_user + "'";
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, employee.getPassword());
            pst.execute();
            
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al modificar la contraseña");
            return false;
        }
    }
}
 