package javaFX;

import data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ProductPrice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        dataList = FXCollections.observableArrayList();
        products = new ArrayList<>();
        table.setItems(dataList);

        bLoadData.setOnAction(event -> {
            loadData();
            tab1.setDisable(false);
            tab2.setDisable(false);
            tab3.setDisable(false);
            tab4.setDisable(false);
        });

        bChangePrices.setOnAction(event -> {
            String number = tfProcentValue.getText();

            if (isNumeric(number)){
                String sign = tfProcentValue.getText().substring(0,1);
                double value = Double.parseDouble(tfProcentValue.getText().substring(1));

                if (sign.equals("+")) {
                    System.out.println("Podwyzka o: " + value + "%");
                    System.out.println();
                    new ProductPrice(taResults).increaseDecrease(
                            products.toArray(new Product[products.size()]), value, 0
                    );
                } else {        // sign: -
                    System.out.println("Obnizka o: " + value + "%");
                    System.out.println();
                    new ProductPrice(taResults).increaseDecrease(
                            products.toArray(new Product[products.size()]), value, 1
                    );
                }
            } else {
                System.out.println("Niepoprawny format");
            }
        });
    }

    private void updateDataList(double value, ProductPrice productPrice, int sign) {
//        dataList.clear();
//        Product[] newProducts = productPrice.increaseDecrease(
//                products.toArray(new Product[products.size()]), value, sign
//        );
//        dataList.add(newProducts);
    }

    private void loadData() {
        dataList.clear();
        products.clear();
        String CsvFile = "dane_aplikacji.csv";
        String FieldDelimiter = ";";

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter);

                Product product = new Product(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]));
                dataList.add(product);
                products.add(product);
            }

//            System.out.println("prod. id: " + products.get(0).getId());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("((-)|(\\+))\\d+(\\.\\d+)?");
    }
}
