package javaFX;

import data.Product;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ShowProducts;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, Integer> columnID, columnAmount;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Double> columnPrice;

    @FXML
    private Button bLoadData, bChangePrices, bCalculateAssortment, bShowProducts, bCalculateNetto;

    @FXML
    private TextField tfProcentValue, tfLimitValue;

    @FXML
    private TextArea taResults;

    @FXML
    private Tab tab1, tab2, tab3, tab4;

    private ObservableList dataList;
    private ArrayList<Product> products;
    OutputStream out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        dataList = FXCollections.observableArrayList();
        table.setItems(dataList);
        products = new ArrayList<>();

        tfLimitValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfLimitValue.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        bLoadData.setOnAction(event -> {
            dataList.clear();
            products.clear();
            loadData();
            tab1.setDisable(false);
            tab2.setDisable(false);
            tab3.setDisable(false);
            tab4.setDisable(false);
        });

        bShowProducts.setOnAction(event -> {
            taResults.clear();
            if(!tfLimitValue.getText().isEmpty()){
                ShowProducts showProducts = new ShowProducts(out);
                showProducts.underLimit(products, Integer.parseInt(tfLimitValue.getText()));
            }
        });
    }

    private void loadData() {
        String CsvFile = "dane_aplikacji.csv";
        String FieldDelimiter = ";";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter);

                Product product = new Product(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]));
                products.add(product);
                dataList.add(product);
            }
//            System.out.println("prod. id: " + products.get(0).getId());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendText(String text){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                taResults.appendText(text);
            }
        });
    }
}
