package model;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "models")
public class ModelEntity extends Model {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Positive
    @Column(name = "startYear", nullable = false)
    private int yearOfProductionStart;

    @Positive
    @Column(name = "endYear")
    private int yearOfProductionEnd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfProductionStart() {
        return yearOfProductionStart;
    }

    public void setYearOfProductionStart(int yearOfProductionStart) {
        this.yearOfProductionStart = yearOfProductionStart;
    }

    public int getYearOfProductionEnd() {
        return yearOfProductionEnd;
    }

    public void setYearOfProductionEnd(int yearOfProductionEnd) {
        this.yearOfProductionEnd = yearOfProductionEnd;
    }


    @Override
    public String toString() {
        return "ModelEntity{" +
                "name='" + name + '\'' +
                ", yearOfProductionStart=" + yearOfProductionStart +
                ", yearOfProductionEnd=" + yearOfProductionEnd +
                '}';
    }
}
