package productospharma.modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Categoria {

    private int id;
    private String descripcion;
    private String nombre;
    private String subcategoria;
   
    
    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

   

    @Override
    public String toString() {
            return "Categoria{" +
           "id=" + id +
           ", descripcion=" + descripcion +
           ", nombre=" + nombre +
           ", subcategoria=" + subcategoria +
           '}';
    }
    
}
