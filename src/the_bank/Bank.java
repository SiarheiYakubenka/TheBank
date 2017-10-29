package the_bank;

import the_bank.accounts.Account;
import the_bank.accounts.DebitAccount;
import the_bank.customers.Customer;
import the_bank.customers.CustomerComparator;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Bank {

    private static Map<Customer, ArrayList<Storable>> customersList;

    static {
        customersList = new TreeMap<>(new CustomerComparator());
    }

    public static Map<Customer, ArrayList<Storable>> getCustomersList() {
        return customersList;
    }

    public static void addCustomer(Customer customer, Account account){
        if (customer == null || account == null) throw new NullPointerException();
        if (!customersList.containsKey(customer)){
        customersList.put(customer, new ArrayList<>());
        customersList.get(customer).add(account);
        }else {
            System.out.println("Клиент уже существует");
        }
    }

    public static void addCustomer(String name, String secondName, String passportID, Account account){
        if (account == null) throw new NullPointerException();
        Customer customer = new Customer(name, secondName, passportID);
        if (!customersList.containsKey(customer)){
        customersList.put(customer, new ArrayList<>());
        customersList.get(customer).add(account);
        System.out.println("Клиент успешно добавлен");
        System.out.println(customer);
        System.out.println(account);
        }else {
            System.out.println("Клиент уже существует");
        }
    }

    public static void deleteClient(String id){
        Customer customer1 = new Customer("", "", id);
        if (!customersList.isEmpty()) {
            for (Customer customer : customersList.keySet()) {
                if (customer.equals(customer1)){
                customersList.remove(customer);
                Account.decrCount();
                break;
                }
            }
        }
        else{
            System.out.println("Клиента не существует");
        }
    }

    public static void getClientsInfo(){
        if (customersList.isEmpty()) return;
        for (Customer customer : customersList.keySet()) {
            System.out.println(customer);
            for (Storable storage : customersList.get(customer))
                System.out.println(storage);
        }
    }

    public static void addStorage(Customer customer, Storable storage){
        if (customer == null || storage == null) throw new NullPointerException();
        if (customersList.containsKey(customer))
            customersList.get(customer).add(storage);
        else
            System.out.println("Нельзя добавить аккаунт или ячейку. Клиента не существует");
    }

    public static void takeCommissionAndInterests(){

        if (customersList.isEmpty()) return;
        for (Customer customer : customersList.keySet()) {
            Account account = (Account) customersList.get(customer).get(0);
            for (Storable storage :  customersList.get(customer)) {
                if (storage instanceof Account){
                    storage.takeOffCommission((Account) storage);
                    ((Account) storage).calcOfInterest();

                }else {
                    storage.takeOffCommission(account);
                }

            }
        }
    }

    public static  void accrualInterest(){

        if (customersList.isEmpty()) return;
        for (Customer customer : customersList.keySet())
            for (Storable storage :  customersList.get(customer))
                if (storage instanceof Account)
                    ((Account) storage).accrualOfInterest();


    }

}
