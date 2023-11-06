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
import productospharma.modelo.Producto;

public class FXMLProductoController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField doublePrecio;


    @FXML
    private TextField numStock;

    @FXML
    private TextArea txtAreaDescripcion;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TableView<Producto> tvProductos;
    
    private ProductoDao productoDao;
    
    private ContextMenu cmOpciones;
    
    private Producto productoSelect;


    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoDao = new ProductoDao();
        cargarProductos();

        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvProductos.getSelectionModel().getSelectedIndex();
                productoSelect = tvProductos.getItems().get(index);

                System.out.println(productoSelect);

                txtNombre.setText(productoSelect.getNombre());
                doublePrecio.setText(String.valueOf(productoSelect.getPrecio()));
                numStock.setText(String.valueOf(productoSelect.getStock()));
                txtCategoria.setText(productoSelect.getCategoria());
                txtAreaDescripcion.setText(productoSelect.getDescripcion());

                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvProductos.getSelectionModel().getSelectedIndex();

                Producto productoEliminar = tvProductos.getItems().get(index);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea eliminar Producto ?" + productoEliminar.getNombre() + "?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    boolean rsp = productoDao.eliminar(productoEliminar.getId());
                    if (rsp) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Exito");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Se elimino Correctamente el Producto");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();

                        cargarProductos();

                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);

                        alert2.setTitle("Error");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Hubo un Error al eliminar el Producto");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();
                    }

                }
            }

        });
        tvProductos.setContextMenu(cmOpciones);

    }

    @FXML
    void btnGuardarOnAction(ActionEvent event) {

        if (productoSelect == null) {
            Producto producto = new Producto();

            producto.setNombre(txtNombre.getText());

            try {
                double precio = Double.parseDouble(doublePrecio.getText());
                producto.setPrecio(precio);

                if (precio > 0) {
                    producto.setPrecio(precio);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El precio debe ser mayor que $0.00");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                // Manejar el caso en el que el texto no sea un número válido
                System.err.println("Error al convertir el precio a double: " + e.getMessage());
            }

            try {
                int stock = Integer.parseInt(numStock.getText());
                producto.setStock(stock);
                if (stock > 0) {
                    producto.setStock(stock);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El Stock debe ser Mayor a 0 Piezas");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                // Manejar el caso en el que el texto no sea un número válido
                System.err.println("Error al convertir el stock a int: " + e.getMessage());
            }
            producto.setCategoria(txtCategoria.getText());
            producto.setDescripcion(txtAreaDescripcion.getText());

            System.out.println(producto.toString());

            boolean rsp = this.productoDao.registrar(producto);
            if (rsp) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Se registro Correctamente el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                cargarProductos();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Hubo un Error al registrar el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        } else {
            productoSelect.setNombre(txtNombre.getText());
            productoSelect.setPrecio(Double.parseDouble(doublePrecio.getText()));
            productoSelect.setStock(Integer.parseInt(numStock.getText()));
            productoSelect.setCategoria(txtCategoria.getText());
            productoSelect.setDescripcion(txtAreaDescripcion.getText());

            boolean rsp = this.productoDao.editar(productoSelect);

            if (rsp) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Se guardo Correctamente el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                cargarProductos();

                productoSelect = null;
                btnCancelar.setDisable(true);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Hubo al EDITAR al guardar el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }

    }
    private void limpiarCampos(){
        txtNombre.setText("");
        doublePrecio.clear();
        numStock.clear();
        txtCategoria.setText("");
        txtAreaDescripcion.setText("");
    }
    
    public void cargarProductos(){
        tvProductos.getItems().clear();
        tvProductos.getColumns().clear();
        
        List<Producto> producto = this.productoDao.listar();
        
        ObservableList<Producto> data = FXCollections.observableArrayList(producto);
        
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory("precio"));

        TableColumn stockCol = new TableColumn("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory("stock"));
        
        TableColumn categoriaCol = new TableColumn("Categoria");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));
        
        TableColumn descrpcionCol = new TableColumn("Descripcion");
        descrpcionCol.setCellValueFactory(new PropertyValueFactory("descripcion"));
        
        tvProductos.setItems(data);
        tvProductos.getColumns().addAll(idCol,nombreCol,precioCol,stockCol,categoriaCol,descrpcionCol);
    }
    
    @FXML
    void btnCancelarOnAction(ActionEvent event) {
        productoSelect = null;
        limpiarCampos();
        btnCancelar.setDisable(true);
        
    } 

   
}
