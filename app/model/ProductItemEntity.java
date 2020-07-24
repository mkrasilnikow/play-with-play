package model;

import io.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductItemEntity extends Model {
    @Id
    @SequenceGenerator(name="seq_PK",sequenceName="ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_PK")
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "name")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "name")
    private ModelEntity model;

    @Positive
    @Column(name = "startYear", nullable = false)
    private int yearOfProduction;

    @PositiveOrZero
    @Column(name = "mileage")
    private int mileage;

    @PositiveOrZero
    @Column(name = "price")
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
