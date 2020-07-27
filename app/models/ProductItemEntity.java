package models;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProductItemEntity {
    private int id;
    private BrandEntity brand;
    private ModelEntity model;
    @Positive
    private int yearOfProduction;
    @PositiveOrZero
    private int mileage;
    @PositiveOrZero
    private BigDecimal price;

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
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
