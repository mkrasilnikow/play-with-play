package models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class ProductItemEntity {
    private int id;
    private String brand;
    private String model;
    private int yearOfProduction;
    private int mileage;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductItemEntity{" +
                "id='" + id + '\'' +
                ", brand=" + brand.toString() +
                ", model=" + model.toString() +
                ", yearOfProduction=" + yearOfProduction +
                ", mileage=" + mileage +
                ", price=" + price +
                '}';
    }
}
