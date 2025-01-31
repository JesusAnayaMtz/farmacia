
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

public class CategoriesDao {
    
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
    
    //Resgistrar Categoria
    public boolean registerCategorieQuery(Categories category){
        String query = "INSERT INTO categories (name, created, updated) VALUES (?,?,?)";
        
        Timestamp datetime = new Timestamp(new Date().getTime());
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1,category.getName());
            pst.setTimestamp(2, datetime);
            pst.setTimestamp(3, datetime);
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar la categoria " + e);
            return false;
        }
        
    }
    
    
    //Listar clientes o busqueda de cliente
    public List listCategoryQuery(String value){
        List<Categories> list_categories = new ArrayList();
        
        //creaos nuestra query
        String query = "SELECT * FROM categories ORDER BY name ASC";
        //query para buscar un empleado
        String query_search_category = "SELECT * FROM categories WHERE id LIKE '%" + value + "%'";
        
        try{
            conn = cn.getConnection();
            
            //si el usuario no ingresa nada en el cuadro de busqueda se ejcuta el if
            if(value.equalsIgnoreCase("")){
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {  //si el usuario ingresa un dato en la busqueda se ejecuta el 2 query
                pst = conn.prepareStatement(query_search_category);
                rs = pst.executeQuery();
            }
             //si encuentra registros recoirre el la lista cuando se consulta
            while(rs.next()){
                
                Categories category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                
                list_categories.add(category);
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,  e.toString());
        }
        
        return list_categories;
}
    
    
    //Modificar Cliente
    public boolean updateCategoryQuery(Categories category){
        //Creamos nuestra query que le vamos a pasar para agregar un empleado nuevo
        String query = "UPDATE categories SET name = ?, update = ? WHERE id = ?";
        //esta variable se utilizara para el create y el update
        Timestamp datetime = new Timestamp(new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            //accedemos a los setter del empleado para registrar los datos
            pst.setString(1, category.getName());
            pst.setTimestamp(2, datetime);
            pst.setInt(3, category.getId());
            
            //ejecutamos la sentencia sql
            pst.execute();
            
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar la categoria " + e);
            return false;
        }
    }
    
    //Eliminar cliente
    public boolean deleteCategoryQuery(int id){
        String query = "DELETE FROM categories WHERE ID = " + id;
        
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar una categoria que tenga relacion con otra tabla ");
            return false;
        }
        }
    
}
