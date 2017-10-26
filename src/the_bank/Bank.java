package the_bank;

import the_bank.accounts.Account;
import the_bank.customers.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Customer, ArrayList<Storable>> customersList;

    public Bank(){
        customersList = new HashMap<>();
    }

    public void addCustomer(Customer customer, Account account){
        customersList.put(customer, new ArrayList<>());
        customersList.get(customer).add(account);
    }

    public void addCustomer(String name, String secondName, String passportID, Account account){
        Customer customer = new Customer(name, secondName, passportID);
        customersList.put(customer, new ArrayList<>());
        customersList.get(customer).add(account);
    }
}
