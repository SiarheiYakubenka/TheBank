package the_bank;

import the_bank.accounts.*;
import the_bank.customers.Customer;
import the_bank.deposit_boxes.DepositBox;
import the_bank.deposit_boxes.PreciousMetals;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        try {
            Account account1 = new DebitAccount(new BigDecimal("500"), Currency.valueOf("BYN"));
            Operation.replenish(account1, new BigDecimal("95"), Currency.BYN);
            Account account2 = new CreditAccount(new BigDecimal("100"), Currency.USD);
            Operation.transfer(account2, account1, new BigDecimal("50"), Currency.USD);

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (NegativeNumberException e){
            System.out.println(e.getMessage()+" "+e.getValueName());
        }
    }
}
