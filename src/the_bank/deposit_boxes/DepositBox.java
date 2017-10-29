package the_bank.deposit_boxes;

import the_bank.Storable;
import the_bank.accounts.Account;
import the_bank.accounts.Currency;
import the_bank.accounts.Operation;

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
    public void takeOffCommission(Account account) {
        BigDecimal commission, amount;

        if(values instanceof Shares){
            commission = new BigDecimal("0.1");
            amount = new BigDecimal("100");
            takeCommission(account, commission, amount );
        }
        if(values instanceof PreciousMetals){
            commission = new BigDecimal("0.2");
            amount = new BigDecimal("50");
            takeCommission(account, commission, amount );
        }
    }

    private void takeCommission(Account account, BigDecimal commission, BigDecimal amount){
        if (getSumm().compareTo(amount)>0) {
            BigDecimal b = getSumm().divide(amount, 0, BigDecimal.ROUND_HALF_UP);
            commission = commission.multiply(b).setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        if(!Operation.withdraw(account, commission, Currency.BYN))
            System.out.println("Недостаточно средств для снятия коммиссии");
    }

    @Override
    public String toString() {
        if(values instanceof Shares){

            return String.format("Содержимое ячейки ");
        }
        if(values instanceof PreciousMetals){
            return String.format("%nСодержимое ячейки: %n"+((PreciousMetals) values).getName()+
            "%nКоличество %d г. %nЦенность %.2f BYN", getQuantity(), getSumm());
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
