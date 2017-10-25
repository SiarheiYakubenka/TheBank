package the_bank.accounts;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    public CreditAccount(BigDecimal amount, Currency currency) {
        super(amount, currency);
    }

    @Override
    public String toString() {
        return String.format("%nТип счета: Кредитный"+super.toString());
    }
}
