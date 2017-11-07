package the_bank.deposit_boxes;

import java.math.BigDecimal;

public enum PreciousMetal implements Valuable {
    SILVER("Серебро",1),
    GOLD("Золото",81),
    PLATINUM("Платина",58),
    PALLADIUM("Палладий",59);

    private double value;
    private String name;

    PreciousMetal(String name, double value){
        this.name = name;
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Цена металла не может быть <= 0");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "PreciousMetal{" +
                "name='" + name + '\'' +
                " ,value=" + value +
                "} ";
    }

    public String getName() {
        return name;
    }
}
