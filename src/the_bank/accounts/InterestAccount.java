package the_bank.accounts;

import java.math.BigDecimal;

public class InterestAccount extends Account {

    public InterestAccount(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }


    @Override
    public String toString() {
        return String.format("%nТип счета: Процентный"+super.toString());
    }
}
