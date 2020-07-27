package models;

import javax.validation.constraints.Positive;

public class ModelEntity {
    private String name;
    @Positive
    private int yearOfProductionStart;
    @Positive
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
