package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty amount;

    public Product(Integer id, String name, Double price, Integer amount) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.amount = new SimpleIntegerProperty(amount);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
