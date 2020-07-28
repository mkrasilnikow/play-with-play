package models;

public class ModelEntity {
    private String name;
    private int yearOfProductionStart;
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
