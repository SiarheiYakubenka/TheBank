package the_bank.accounts;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    public CreditAccount(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public void takeOffCommission() {
        setBalance(getBalance().subtract(getCurrency().getCommission()));
    }

    @Override
    public boolean withdraw(BigDecimal amount, Currency currency) {
        if(this.getCurrency() == currency){
                setBalance(getBalance().subtract(amount));
                return true;

        } else {
            BigDecimal b = Exchange.Convert(currency, getCurrency(), amount);
                setBalance(getBalance().subtract(b));
                return true;
        }
    }

    @Override
    public String toString() {
        return String.format("%nТип счета: Кредитный"+super.toString());
    }
}
