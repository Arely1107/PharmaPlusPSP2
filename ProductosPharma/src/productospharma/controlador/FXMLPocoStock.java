package productospharma.controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javax.swing.text.StyleConstants;
import productospharma.Dao.ProductoDao;
import productospharma.modelo.Producto;

public class FXMLPocoStock implements Initializable {

    
    @FXML
    private TableView<Producto> tvProductos;
    
    private ProductoDao productoDao;
    
    private ContextMenu cmOpciones;
    
    private Producto productoSelect;

    private Button expotToXl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoDao = new ProductoDao();
    cargarProductosConStockBajo();
   
    
    }
    
   public void cargarProductosConStockBajo() {
    tvProductos.getItems().clear();
    tvProductos.getColumns().clear();

    List<Producto> productosConStockBajo = this.productoDao.listarConStockBajo();

    ObservableList<Producto> data = FXCollections.observableArrayList(productosConStockBajo);

    TableColumn<Producto, Integer> idCol = new TableColumn<>("Id");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Producto, String> nombreCol = new TableColumn<>("Nombre");
    nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    TableColumn<Producto, Double> precioCol = new TableColumn<>("Precio");
    precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));

    TableColumn<Producto, Integer> stockCol = new TableColumn<>("Stock");
    stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    TableColumn<Producto, String> categoriaCol = new TableColumn<>("Categoria");
    categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));

    TableColumn<Producto, String> descripcionCol = new TableColumn<>("Descripcion");
    descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

    tvProductos.setItems(data);
    tvProductos.getColumns().addAll(idCol, nombreCol, precioCol, stockCol, categoriaCol, descripcionCol);
}



   
}