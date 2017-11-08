package the_bank;

import the_bank.accounts.*;
import the_bank.accounts.Currency;
import the_bank.customers.Customer;
import the_bank.deposit_boxes.DepositBox;

import java.math.BigDecimal;
import java.util.*;

public class Bank {

    static Set<Customer> clients;

    static Map<Long, Account> accounts;
    static Map<Long, DepositBox> storages;

    static Map<Customer, Set<Long>> clientAccounts;
    private static Map<Customer, Set<Long>> clientStorages;

    private static List<AbleToStore> commissions;

    private static long accountId;
    private static long storageId;

    static {

        clients = new HashSet<>();
        accounts = new HashMap<>();
        storages = new HashMap<>();

        clientStorages = new HashMap<>();
        clientAccounts = new HashMap<>();
        accountId = 1;
        storageId = 1;
        commissions = new ArrayList<>();
    }


    public static void addClient(String name, String secondName, String passportID){
        Customer customer = new Customer(name, secondName, passportID);
        clients.add(customer);
        clientAccounts.put(customer, new HashSet<>());
        clientStorages.put(customer, new HashSet<>());
    }

    public static Long addNewDebitAccount(Customer customer, Currency currency) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        Account account = new DebitAccount(currency);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        commissions.add(account);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static Long addNewPercentAccount(Customer customer, Currency currency, float percent) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        PercentAccount account = new PercentAccount(currency, percent);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        commissions.add(account);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static Long addNewCreditAccount(Customer customer, Currency currency, float percent) {
        if (customer == null) {
            throw new IllegalArgumentException("Нельзя создать счет для несущуствующего клиента");
        }
        CreditAccount account = new CreditAccount(currency, percent);
        accounts.put(accountId, account);
        clientAccounts.get(customer).add(accountId);
        commissions.add(account);
        long temp = accountId;
        accountId++;
        return temp;
    }

    public static void depositOnAccount(Customer customer, BigDecimal amount, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(id).deposit(amount, accounts.get(id).getCurrency());
    }

    public static void withdrawFromAccount(Customer customer, BigDecimal amount, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(id).withdraw(amount, accounts.get(id).getCurrency());
    }

    public static BigDecimal getAccountBalance(Customer customer, long id) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(customer).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        return accounts.get(id).getBalance();
    }

    public static void transfer(Customer customer, BigDecimal amount, Currency currency, long source, long destination) {
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(source) || !accounts.containsKey(destination)) {
            throw new IllegalArgumentException("Cчета не существует");
        }
        if (!clientAccounts.get(customer).contains(source)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(source).transfer(accounts.get(destination), amount, currency);
    }

    public static boolean searchClientByPassportNumber(String passportNumber) {
        Customer fakeClient = new Customer("_", "_", passportNumber);
        return clients.contains(fakeClient);
    }

    public static boolean searchAccountById(long id){

        return accounts.containsKey(id);
    }


    public static void addStorage(Customer customer, DepositBox storage){
        if (customer == null || !clients.contains(customer)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (storage == null){
            throw new IllegalArgumentException("storage = null");
        }
        storages.put(storageId, storage);

    }

    public static void takeCommissionAndPercent(){

        if (commissions.isEmpty()) return;
        for (AbleToStore storage : commissions) {
            storage.takeOffCommission();
            if (storage instanceof CreditAccount){
                ((CreditAccount) storage).calcOfPercent();
            }else if (storage instanceof PercentAccount){
                ((PercentAccount) storage).calcOfPercent();
            }
        }


    }

    public static  void  recalcMonthOutcome(){

        if (accounts.isEmpty()) return;
        for (Map.Entry<Long, Account> entry : accounts.entrySet()){
            if (entry.getValue() instanceof CreditAccount){
                ((CreditAccount) entry.getValue()).calcOfPercent();
            }else if (entry.getValue() instanceof PercentAccount){
                ((PercentAccount) entry.getValue()).calcOfPercent();
            }
        }
    }

}
