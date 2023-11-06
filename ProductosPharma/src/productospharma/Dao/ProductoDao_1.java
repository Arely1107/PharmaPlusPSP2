package productospharma.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import productospharma.conexion.ConexionMysql;
import productospharma.modelo.Categoria;

public class ProductoDao_1 {
    private ConexionMysql fabricaConexion;
    
    public ProductoDao_1(){
        this.fabricaConexion = new ConexionMysql();
            
    }
    
    public List<Categoria> listar(){
        List<Categoria> listaCategoria = new ArrayList<>();
        try {

            String SQL = "SELECT * FROM productos.categoria;";
            Connection connection = this.fabricaConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);
            ResultSet data = sentencia.executeQuery();
            
            while(data.next()== true){
                Categoria categoria = new Categoria();
                categoria.setId(data.getInt(1));
                categoria.setDescripcion(data.getString(2));
                categoria.setNombre(data.getString(3));
                categoria.setSubcategoria(data.getString(4));
                
                listaCategoria.add(categoria);
            }
            data.close();
            sentencia.close();
        } catch (Exception e) {
            System.err.println("Ocurrio al Mostrar Lista de Categorias");
            System.err.println("Mensaje del error :" + e.getMessage());
            System.err.println("Detalles del error: ");
            
            e.printStackTrace();
        }
        return listaCategoria;
    }
    
}
