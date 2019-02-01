package javaFX;

import data.Product;
import rate.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
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
    private Product[] p;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));

        dataList = FXCollections.observableArrayList();
        table.setItems(dataList);

        p = new Product[dataList.size()];

        for (int i = 0; i < dataList.size(); i++)
        {
            p[i] = (Product) dataList.get(i);
        }

        taResults.setEditable(false);

        bLoadData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataList.clear();
                loadData();
                tab1.setDisable(false);
                tab2.setDisable(false);
                tab3.setDisable(false);
                tab4.setDisable(false);
            }
        });

        bCalculateAssortment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Value.count(p, );
            }
        });

        bCalculateNetto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
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
                dataList.add(product);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
