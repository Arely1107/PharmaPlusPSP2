package productospharma.controlador;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import productospharma.Dao.ProductoDao;
import productospharma.Dao.ProductoDao_1;
import productospharma.modelo.Categoria;
import productospharma.modelo.Producto;

public class FXMLProductoController_1 implements Initializable {

    @FXML
    private TableView<Categoria> tvCategorias;
    
    private ProductoDao_1 productoDao_1;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoDao_1 = new ProductoDao_1();
        cargarCategorias();
    }
    public void cargarCategorias(){
        tvCategorias.getItems().clear();
        tvCategorias.getColumns().clear();
        
        List<Categoria> categoria = this.productoDao_1.listar();
        
        ObservableList<Categoria> data = FXCollections.observableArrayList(categoria);
        
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn descCol = new TableColumn("Descripcion");
        descCol.setCellValueFactory(new PropertyValueFactory("descripcion"));

        TableColumn nomCol = new TableColumn("Nombre");
        nomCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        
        TableColumn subCol = new TableColumn("Subcategoria");
        subCol.setCellValueFactory(new PropertyValueFactory("subcategoria"));
        
        tvCategorias.setItems(data);
        tvCategorias.getColumns().addAll(idCol,descCol,nomCol,subCol);
    }



   
}