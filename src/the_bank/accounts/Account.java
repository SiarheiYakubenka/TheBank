package the_bank.accounts;

import the_bank.Storable;
import the_bank.NegativeNumberException;

import java.math.BigDecimal;
import java.util.Locale;

public abstract class Account implements Storable {
    private BigDecimal balance;
    private final Currency currency;
    private final String id;
    private static int count;

    static {
        count = 0;
    }

    Account(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) throw new NullPointerException("Счет или валюта = null");
        if(amount.signum()<0) throw new NegativeNumberException("Сумма не может быть отрицательной", amount.toString());
        this.currency = currency;
        setBalance(amount);
        count++;
        id = String.format("%s%h%08d", currency, this.hashCode(), count);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static int getCount() {
        return count;
    }

    public static void decrCount(){
        count--;
    }

    void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, BigDecimal.ROUND_FLOOR);
    }

    public abstract void calcOfInterest();

    public abstract void  accrualOfInterest();

    @Override
    public void takeOffCommission(Account account) {
        if (this instanceof CreditAccount || getBalance().compareTo(getCurrency().getCommission())>=0)
            setBalance(getBalance().subtract(getCurrency().getCommission()));
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(String.format(Locale.FRANCE,
                "%nid счета: "+id
                        +"%nВалюта счета: " + getCurrency().getDescription()
                        + "%nБаланс счета составляет: %.2f " + getCurrency(), getBalance()));
        return out.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return  false;
        }
        if (obj == this){
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return  false;
        }
        Account other = (Account) obj;
        if (this.getCurrency().compareTo(other.getCurrency()) == 0){
            return this.getBalance().compareTo(other.getBalance()) == 0;
        }else {
            BigDecimal b = Exchange.Convert(other.getCurrency(), getCurrency(), other.getBalance());
            return this.getBalance().compareTo(b) == 0;
        }
    }

}
