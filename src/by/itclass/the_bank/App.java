package by.itclass.the_bank;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {

        BigDecimal amount = new BigDecimal("100.129");
        Currency currency = Currency.valueOf("BYN");
        Account acc = new DebitAccount(amount, currency);
        System.out.println(acc);

        try {
            acc.replenish(new BigDecimal("100"), Currency.valueOf("BYN"));
        } catch (IllegalArgumentException e){
            System.out.println("Возникла ошибка: "+e.getMessage() );
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        System.out.println(acc);

        Account acc1 = new DebitAccount(new BigDecimal("1"), Currency.valueOf("EUR"));

        System.out.println(acc1);
        acc1.takeOffCommission();
        System.out.println(acc1);
        int transfer = acc1.transfer(acc, new BigDecimal("100"), Currency.USD);
        if(transfer == -1)
            System.err.println("Недостаточно средств для перевода");
        else if (transfer == 0)
            System.err.println("Нельзя перевести на этот же счет");

    }
}
