package by.itclass.the_bank;

import java.math.BigDecimal;

public class DebitAccount extends Account {

    public DebitAccount(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public void takeOffCommission() {
        if (getBalance().compareTo(getCurrency().getCommission())>=0)
            setBalance(getBalance().subtract(getCurrency().getCommission()));
    }

    @Override
    public boolean withdraw(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) throw new NullPointerException();
        else if(amount.signum()<0) throw new IllegalArgumentException("Сумма не может быть отрицательной");

        if(this.getCurrency() == currency){
            if (getBalance().compareTo(amount) >= 0) {
                setBalance(getBalance().subtract(amount));
                return true;
            }
        } else {
                BigDecimal b = Exchange.Convert(currency, getCurrency(), amount);
                if (getBalance().compareTo(b) >= 0){
                    setBalance(getBalance().subtract(b));
                    return true;
                }
        }
        return false;
    }

    @Override
    public String toString() {
        return  String.format("%nТип счета: Дебетовый"+super.toString());
    }
}
