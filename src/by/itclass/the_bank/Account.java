package by.itclass.the_bank;

import java.math.BigDecimal;
import java.util.Locale;

public abstract class Account implements AbleToStore {
    private BigDecimal balance;
    private final Currency currency;


    public Account(BigDecimal amount, Currency currency) {
        if(amount== null || currency == null) throw new NullPointerException();
        else if(amount.signum()<0) throw new IllegalArgumentException("Сумма не может быть отрицательной");


        balance = amount.setScale(2, BigDecimal.ROUND_FLOOR);
        this.currency = currency;
    }


    public BigDecimal getBalance() {
        return balance;
    }
    public Currency getCurrency() {
        return currency;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, BigDecimal.ROUND_FLOOR);
    }


    public abstract boolean withdraw(BigDecimal amount, Currency currency);

    public  void replenish(BigDecimal amount, Currency currency){
        if (amount == null || currency == null) throw new NullPointerException("amount или currency = null");
        else if(amount.signum()<0) throw new IllegalArgumentException("Сумма не может быть отрицательной");

        if(this.getCurrency() == currency)
        setBalance(getBalance().add(amount));
        else {
            BigDecimal b = Exchange.Convert(currency, getCurrency(), amount);
            setBalance(getBalance().add(b));
        }
    }

    public int transfer(Account account, BigDecimal amount, Currency currency){
        if (account == null ||amount == null || currency == null) throw new NullPointerException();
        else if(amount.signum()<0) throw new IllegalArgumentException("Сумма не может быть отрицательной");

        if (this != account){
            if(this.withdraw(amount, currency)){
                account.replenish(amount, currency);
                return 1;
            } else return -1;
        }else return 0;
    }

    @Override
    public String toString() {
        String out = String.format(Locale.FRANCE,
                        "%nВалюта счета: "+getCurrency().getDescription()
                        +"%nБаланс счета составляет: %.2f " + getCurrency(), getBalance());
        Currency[] allcurrency = Currency.values();
        for ( Currency toCurrency: allcurrency) {
            if(toCurrency != getCurrency()) {
                BigDecimal b = Exchange.Convert(getCurrency(), toCurrency, getBalance());
                out += String.format("%n%.2f "+toCurrency, b.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }
        return out;
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
            if (this.getBalance().compareTo(other.getBalance()) == 0){
            return true;
            }else return false;
        }else {
            BigDecimal b = Exchange.Convert(other.getCurrency(), getCurrency(), other.getBalance());
            if(this.getBalance().compareTo(b) == 0) return true;
            else return false;
        }
    }
}
