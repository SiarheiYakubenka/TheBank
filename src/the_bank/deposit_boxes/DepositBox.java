package the_bank.deposit_boxes;

import the_bank.AbleToStore;
import the_bank.accounts.Account;
import the_bank.accounts.Currency;
import the_bank.accounts.NotEnoughValuableException;

import java.math.BigDecimal;

public  class DepositBox implements AbleToStore {

    private Valuable values;
    private int amount;
    private Account serviceAccount;

    public DepositBox(Valuable values, Account serviceAccount){
        if (values == null) {
            throw new NullPointerException("Необходимо указать непустое содержимое для ячейки");
        }
        if(serviceAccount == null) {
            throw new NullPointerException("Необхожимо указать непустой аккаунт для обслуживания ячейки");
        }
        this.values = values;
        this.serviceAccount = serviceAccount;
    }

    public Account getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(Account serviceAccount) {
        if (serviceAccount == null) {
            throw new IllegalArgumentException("Аккаунт для обслуживания не может быть пстым");
        }
        this.serviceAccount = serviceAccount;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Вносимое количество должно быть положительным");
        }
        if(values instanceof PreciousMetal && amount >3000) {
            throw new IllegalArgumentException("В ячейке не может храниться более 3 кг");
        }
            this.amount += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cнимаемое количество должно быть положительным");
        }
        if (this.amount < amount) {
            throw new NotEnoughValuableException();
        }
        this.amount -= amount;
    }

    public boolean changeStorableType(Valuable newType) {
        if (amount == 0) {
            this.values = newType;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void takeOffCommission() {
        BigDecimal commission, amount;

        if(values instanceof Shares){
            commission = new BigDecimal("0.1");
            amount = new BigDecimal("100");
            takeCommission(serviceAccount, commission, amount );
        }else {
            commission = new BigDecimal("0.2");
            amount = new BigDecimal("50");
            takeCommission(serviceAccount, commission, amount );
        }
    }

    private void takeCommission(Account account, BigDecimal commission, BigDecimal amount){
        BigDecimal summ;
        summ = new BigDecimal(String.valueOf(this.amount * values.getValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (summ.compareTo(amount)>0) {
            BigDecimal b = summ.divide(amount, 0, BigDecimal.ROUND_HALF_UP);
            commission = commission.multiply(b).setScale(1, BigDecimal.ROUND_HALF_UP);
        }
        account.withdraw(commission, Currency.BYN);
    }

    @Override
    public String toString() {
        return "DepositBox{" +
                "values=" + values +
                ", amount=" + amount +
                '}';
    }


    public int getAmount() {
        return amount;
    }

}
