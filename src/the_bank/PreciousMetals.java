package by.itclass.the_bank;

import java.math.BigDecimal;

public enum PreciousMetals implements Valuable {
    SILVER("1"),
    GOLD("81"),
    PLATINUM("58"),
    PALLADIUM("59");

    private BigDecimal value;

    PreciousMetals(String value){
        this.value = new BigDecimal(value);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value);
    }
}
