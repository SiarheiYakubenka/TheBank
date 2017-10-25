package the_bank.accounts;

import the_bank.NegativeNumberException;

import java.math.BigDecimal;

public class Operation {

    public static void replenish(Account account, BigDecimal amount, Currency currency){
        if (account == null || amount == null || currency == null)
            throw new NullPointerException("account, amount или currency = null");
        else if(amount.signum()<0)
            throw new NegativeNumberException("%nОперация закончилась неудачей. Сумма не может быть отрицательной",
                amount.toString());

        if(account.getCurrency() == currency)
            account.setBalance(account.getBalance().add(amount));
        else {
            BigDecimal b = Exchange.Convert(currency, account.getCurrency(), amount);
            account.setBalance(account.getBalance().add(b));
        }

        System.out.printf("%n%nОперация прошла успешно. Баланс пополнен на %.2f %s %n " + account,
                amount, currency);
    }

    public static int transfer(Account account1, Account account2, BigDecimal amount, Currency currency){
        if (account1 == null || account2 == null ||amount == null || currency == null) throw new NullPointerException();
        else if(amount.signum()<0) throw new NegativeNumberException("Сумма не может быть отрицательной",
                amount.toString());

        if (account1 != account2){
            if(withdraw(account1, amount, currency)){
                replenish(account2, amount, currency);
                return 1;
            } else return -1;
        }else return 0;
    }

    public static boolean withdraw(Account account, BigDecimal amount, Currency currency){
        if (amount == null || currency == null) throw new NullPointerException();
        else if(amount.signum()<0) throw new NegativeNumberException("Сумма не может быть отрицательной",
                amount.toString());

        if (account instanceof CreditAccount || account.getBalance().compareTo(amount) >= 0){
            if(account.getCurrency() == currency){

                account.setBalance(account.getBalance().subtract(amount));
                System.out.printf("%n%nОперация прошла успешно. Со счета сняли %.2f %s %n " + account,
                        amount, currency);
                return true;
            }
            else {
                BigDecimal convertedAmount = Exchange.Convert(currency, account.getCurrency(), amount);
                account.setBalance(account.getBalance().subtract(convertedAmount));
                System.out.printf("%n%nОперация прошла успешно. Со счета сняли %.2f %s %n " + account,
                        amount, currency);
                return true;
            }
        }
        return false;
    }


}