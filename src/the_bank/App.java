package the_bank;

import the_bank.accounts.*;
import the_bank.customers.Customer;
import the_bank.deposit_boxes.DepositBox;
import the_bank.deposit_boxes.PreciousMetals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Map<Customer, ArrayList<Storable>>  customersList = new HashMap<>();

        Customer customer1 = new Customer("Иван", "Иванов", "НВ122390");

        ArrayList<Storable> storage = new ArrayList<>();
        customersList.put(customer1, storage);

        try {
            Account account1 = new DebitAccount(new BigDecimal("500"), Currency.valueOf("BYN"));
            storage.add(account1);
            customersList.put(customer1, storage);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (NegativeNumberException e){
            System.out.println(e.getMessage()+" "+e.getValueName());
        }



    }
}
