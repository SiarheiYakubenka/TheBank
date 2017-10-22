package the_bank.deposit_boxes;

import java.math.BigDecimal;

public class Shares implements Valuable {

    private final String  name;
    private BigDecimal value;


    public Shares(String name, String value, int sum){
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
