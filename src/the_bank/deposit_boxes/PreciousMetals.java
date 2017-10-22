package the_bank.deposit_boxes;

import java.math.BigDecimal;

public enum PreciousMetals implements Valuable {
    SILVER("Серебро","1"),
    GOLD("Золото","81"),
    PLATINUM("Платина","58"),
    PALLADIUM("Палладий","59");

    private BigDecimal value;
    private String name;

    PreciousMetals(String name, String value){
        this.name = name;
        this.value = new BigDecimal(value);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value);
    }

    public String getName() {
        return name;
    }
}
