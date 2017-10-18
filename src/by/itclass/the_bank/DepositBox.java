package by.itclass.the_bank;

import java.math.BigDecimal;

public  class DepositBox implements AbleToStore {

    private Valuable values;
    private int quantity;
    private BigDecimal summ;

    public DepositBox(Valuable values, int quantity){
       this.values = values;
       this.quantity = quantity;
       summ = values.getValue().multiply(new BigDecimal(String.valueOf(quantity))).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void takeOffCommission() {

    }

    public BigDecimal getSumm() {
        return summ;
    }

    public int getQuantity() {
        return quantity;
    }
}
