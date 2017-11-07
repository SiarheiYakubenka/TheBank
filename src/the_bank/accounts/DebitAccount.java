package the_bank.accounts;

import the_bank.NegativeNumberException;

import java.math.BigDecimal;

public class DebitAccount extends Account {

    public DebitAccount(Currency currency) {
        super(currency);
    }

    @Override
    public void withdraw(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null){
            throw new NullPointerException("Сумма или валюта не может быть пустой");
        }
        if(amount.signum() <= 0){
            throw new NegativeNumberException("Неправильная сумма для снятия", amount.toString());
        }
        if (getBalance().compareTo(amount) < 0){
            throw new NotEnoughValuableException();
        }
        if(getCurrency() == currency){
            setBalance(getBalance().subtract(amount));
        } else {
            BigDecimal convertedAmount = Exchange.Convert(currency, getCurrency(), amount);
            setBalance(getBalance().subtract(convertedAmount));
        }
    }

    @Override
    public String toString() {
        return "DebitAccount{} " +
                "balance=" + getBalance() +
                "currency=" + getCurrency()+
                "}";
    }

}
