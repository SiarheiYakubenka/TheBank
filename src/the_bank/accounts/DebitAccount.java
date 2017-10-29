package the_bank.accounts;

import java.math.BigDecimal;

public class DebitAccount extends Account {

    public DebitAccount(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public void calcOfInterest() {

    }

    @Override
    public void accrualOfInterest() {

    }

    @Override
    public String toString() {
        return  String.format("%nТип счета: Дебетовый"+super.toString());
    }
}
