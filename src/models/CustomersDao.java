
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomersDao {
    //Instaciomaos la conexion
    ConectionMySQL cn = new ConectionMySQL();
    //Estas variables sirve para conectarnos a la base de datos
    Connection conn;
    PreparedStatement pst;  //sirve para las consuktas
    ResultSet rs;  //sirve para obtener datos de la consulta
    
    /*//Variables que nos permitiran datos entre interfaces
    public static int id_customer = 0;
    public static String full_name_customer = "";
    public static String address_customer = "";
    public static String telephone_customer = "";
    public static String email_customer = "";*/

    //Registrar cliente
    public boolean registerCustomerQuery(Customers customer){
        String query = "INSERT INTO customers (id, full_name, address, telephone, email, created, updated) VALUES (?,?,?,?,?,?,?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, customer.getId());
            pst.setString(2,customer.getFull_name());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, datetime);
            pst.setTimestamp(7, datetime);
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar al cliente " + e);
            return false;
        }
        
    }
    
    
    //Listar clientes o busqueda de cliente
    public List listCustomersQuery(String value){
        List<Customers> list_customers = new ArrayList();
        
        //creaos nuestra query
        String query = "SELECT * FROM customers ORDER BY full_name ASC";
        //query para buscar un empleado
        String query_search_customer = "SELECT * FROM customers WHERE id LIKE '%" + value + "%'";
        
        try{
            conn = cn.getConnection();
            
            //si el usuario no ingresa nada en el cuadro de busqueda se ejcuta el if
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {  //si el usuario ingresa un dato en la busqueda se ejecuta el 2 query
                pst = conn.prepareStatement(query_search_customer);
                rs = pst.executeQuery();
            }
             //si encuentra registros recoirre el la lista cuando se consulta
            while(rs.next()){
                
                Customers customer = new Customers();
                customer.setId(rs.getInt("id"));
                customer.setFull_name(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                
                list_customers.add(customer);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,  e.toString());
        }
        
        return list_customers;
}
    
    //Modificar Cliente
    public boolean updateCustomerQuery(Customers customer){
        //Creamos nuestra query que le vamos a pasar para agregar un empleado nuevo
        String query = "UPDATE customers SET full_name = ?, address= ?, telephone= ?, email = ?, update = ? WHERE id = ?";
        //esta variable se utilizara para el create y el update
        Timestamp datetime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //accedemos a los setter del empleado para registrar los datos
            pst.setString(1, customer.getFull_name());
            pst.setString(2, customer.getAddress());
            pst.setString(3, customer.getTelephone());
            pst.setString(4, customer.getEmail());
            pst.setTimestamp(5, datetime);
            pst.setInt(6, customer.getId());
            
            //ejecutamos la sentencia sql
            pst.execute();
            
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente " + e);
            return false;
        }
    }
    
    //Eliminar cliente
    public boolean deleteCustomerQuery(int id){
        String query = "DELETE FROM customers WHERE ID = " + id;
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar un cliente que tenga relacion con otra tabla ");
            return false;
        }
        }
    
}
