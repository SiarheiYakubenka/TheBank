package the_bank;

import the_bank.accounts.Account;
import the_bank.accounts.Currency;
import the_bank.accounts.DebitAccount;
import the_bank.customers.Customer;
import the_bank.deposit_boxes.DepositBox;
import the_bank.deposit_boxes.PreciousMetals;

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

        DepositBox<PreciousMetals> depositBox1 = new DepositBox<>(PreciousMetals.GOLD, 1000 );

        Customer customer1 = new Customer("Jonh", "Dolson", "HB123412");
        customer1.addAccount(acc);
        customer1.addDepositBoxe(depositBox1);
        System.out.println(customer1);

        for (int i = 0; i <customer1.getAccounts().length ; i++) {
            System.out.println(customer1.getAccounts()[i]);
        }

        for (int i = 0; i <customer1.getDepositBoxes().length ; i++) {
            System.out.println(customer1.getDepositBoxes()[i]);
        }

    }
}
