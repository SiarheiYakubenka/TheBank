package the_bank.accounts;

import the_bank.AbleToStore;
import the_bank.NegativeNumberException;

import java.math.BigDecimal;

public abstract class Account implements AbleToStore {
    private BigDecimal balance;
    private final Currency currency;


    Account(Currency currency) {
        if (currency == null) {
            throw new NullPointerException("Валюта не может быть пустой");
        }
        this.currency = currency;
        setBalance(new BigDecimal("0"));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    protected void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    public void deposit(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null)
            throw new NullPointerException("amount или currency = null");
        else if (amount.signum() <= 0)
            throw new NegativeNumberException("Сумма должна быть положительной", amount.toString());

        if (getCurrency() == currency)
            setBalance(getBalance().add(amount));
        else {
            BigDecimal convertedAmount = Exchange.Convert(currency, getCurrency(), amount);
            setBalance(getBalance().add(convertedAmount));
        }
    }

    public abstract void withdraw(BigDecimal amount, Currency currency);

    public  void transfer(Account account, BigDecimal amount, Currency currency){
        withdraw(amount, currency);
        account.deposit(amount, currency);
    }


    @Override
    public void takeOffCommission() {
            withdraw(getCurrency().getCommission(), getCurrency());
    }



}
