package productospharma.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import productospharma.conexion.ConexionMysql;
import productospharma.modelo.Producto;

public class ProductoDao {
    private ConexionMysql fabricaConexion;
    
    public ProductoDao(){
        this.fabricaConexion = new ConexionMysql();
            
    }
    
    public boolean registrar(Producto producto)
    {
        try {
            String SQL="insert into producto(nombre,precio, stock,"
                    + "categoria,descripcion)"
                    +"values(?,?,?,?,?)";
            Connection connection=this.fabricaConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setString(1,producto.getNombre());
            sentencia.setDouble(2,producto.getPrecio());
            sentencia.setInt(3,producto.getStock());
            sentencia.setString(4,producto.getCategoria());
            sentencia.setString(5,producto.getDescripcion());
            
            
            
            sentencia.execute();
            sentencia.close();
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Ocurrio al Registrar el Producto");
            System.err.println("Mensaje del error :" + e.getMessage());
            System.err.println("Detalles del error: ");
            
            e.printStackTrace();
            
            return false;
        }
    }
    
    public List<Producto> listar(){
        List<Producto> listaProdutos = new ArrayList<>();
        try {

            String SQL = "SELECT * FROM productos.producto;";
            Connection connection = this.fabricaConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            
            while(data.next()== true){
                Producto producto = new Producto();
                producto.setId(data.getInt(1));
                producto.setNombre(data.getString(2));
                producto.setPrecio(data.getDouble(3));
                producto.setStock(data.getInt(4));
                producto.setDescripcion(data.getString(5));
                producto.setCategoria(data.getString(6));
                        
                listaProdutos.add(producto);
            }
            data.close();
            sentencia.close();
        } catch (Exception e) {
            System.err.println("Ocurrio al Mostrar Lista de Producto");
            System.err.println("Mensaje del error :" + e.getMessage());
            System.err.println("Detalles del error: ");
            
            e.printStackTrace();
        }
        return listaProdutos;
    }
    
    public List<Producto> listarConStockBajo() {
    List<Producto> listaProductos = new ArrayList<>();
    try {
        String SQL = "SELECT * FROM productos.producto WHERE Stock < 20;";
        Connection connection = this.fabricaConexion.getConnection();
        PreparedStatement sentencia = connection.prepareStatement(SQL);
        ResultSet data = sentencia.executeQuery();

        while (data.next()) {
            Producto producto = new Producto();
            producto.setId(data.getInt(1));
            producto.setNombre(data.getString(2));
            producto.setPrecio(data.getDouble(3));
            producto.setStock(data.getInt(4));
            producto.setDescripcion(data.getString(5));
            producto.setCategoria(data.getString(6));

            listaProductos.add(producto);
        }
        data.close();
        sentencia.close();
    } catch (Exception e) {
        System.err.println("Ocurrió un error al mostrar la lista de productos con stock bajo.");
        System.err.println("Mensaje del error: " + e.getMessage());
        System.err.println("Detalles del error:");
        e.printStackTrace();
    }
    return listaProductos;
}

    
   public boolean editar(Producto producto){
       try {
          
            String SQL = "UPDATE producto SET nombre=?, precio=?, stock=?, categoria=?, descripcion=? WHERE id=?";

            Connection connection=this.fabricaConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            
            sentencia.setString(1,producto.getNombre());
            sentencia.setDouble(2,producto.getPrecio());
            sentencia.setInt(3,producto.getStock());
            sentencia.setString(4,producto.getCategoria());
            sentencia.setString(5,producto.getDescripcion());
            sentencia.setInt(6, producto.getId());
            
            sentencia.executeUpdate();
            
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Ocurrio al Editar Producto");
            System.err.println("Mensaje del error :" + e.getMessage());
            System.err.println("Detalles del error: ");
            
            e.printStackTrace();
        }
       return true;
    }
   
   public boolean eliminar(int id){
       try {
           String SQL = "delete from producto where id =?"; 
           Connection connection = this.fabricaConexion.getConnection();
           PreparedStatement sentencia = connection.prepareStatement(SQL);
           sentencia.setInt(1, id);
           sentencia.executeUpdate();
           sentencia.close();
           
           return true;
           
       } catch (Exception e) {
            System.err.println("Ocurrio al Eliminar Producto");
            System.err.println("Mensaje del error :" + e.getMessage());
            System.err.println("Detalles del error: ");
            
            e.printStackTrace();
            return false;
       }
       
   }
  
}
