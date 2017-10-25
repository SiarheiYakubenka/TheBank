package the_bank.deposit_boxes;

import the_bank.Storable;

import java.math.BigDecimal;

public  class DepositBox<T extends Valuable> implements Storable {

    private T values;
    private int quantity;
    private BigDecimal summ;

    public DepositBox(T values, int quantity){
       this.values = values;
       if(values instanceof PreciousMetals && quantity>3000)
           throw new IllegalArgumentException("В ячейке не может храниться более 3 кг");
       this.quantity = quantity;
       summ = values.getValue().multiply(new BigDecimal(String.valueOf(quantity))).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void takeOffCommission() {
        if(values instanceof Shares){

        }
        if(values instanceof PreciousMetals){

        }
    }

    @Override
    public String toString() {
        if(values instanceof Shares){

            return String.format("Содержимое ячейки ");
        }
        if(values instanceof PreciousMetals){
            return String.format("%nСодержимое ячейки: %n"+((PreciousMetals) values).getName()+
            "%nКоличество %d г. %nЦенность %.2f BYN", quantity, summ);
        }
       return "";
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public int getQuantity() {
        return quantity;
    }
}
