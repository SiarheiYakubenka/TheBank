package the_bank;

import the_bank.accounts.*;
import the_bank.accounts.Currency;
import the_bank.customers.Customer;
import the_bank.deposit_boxes.DepositBox;
import the_bank.deposit_boxes.Shares;

import java.math.BigDecimal;
import java.util.*;

import static the_bank.Bank.*;

public class App {
    private static Scanner sc = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\nКлиенты: " + clients.size() + " " +
                "Счета: " + accounts.size() + " " +
                "Ячейки: " + storages.size());
        System.out.println("Выберите операцию");
        System.out.println("1. Зарегистрировать клиента");
        System.out.println("2. Открыть счет");
        System.out.println("3. Снять деньги со счета");
        System.out.println("4. Положить деньги на счет");
        System.out.println("5. Проверить остаток на счете");
        System.out.println("6. Перевести деньги со счета на счет");
        System.out.println("7. Добавить ячейку");
        System.out.println("0. Выход");
    }

    public static void main(String[] args) {

        final Timer time = new Timer();


        time.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                if(i == 30){
                    i = 0;
                    recalcMonthOutcome();
                }
                takeCommissionAndPercent();
                i++;
            }
        },  86_400_000, 86_400_000);

        int command;
        do {
            command = -1;
            printMenu();
            try {
                command = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            switch (command) {
                case 0:
                    break;
                case 1:
                    registerNewClient();
                    break;
                case 2:
                    registerNewAccount();
                    break;
                case 3:
                    withdrawAccountMoney();
                    break;
                case 4:
                    depositAccountMoney();
                    break;
                case 5:
                    checkAccountMoney();
                    break;
                case 6:
                    transferFromAccToAcc();
                    break;
                case 7:
                    menuAddStorage();
                    break;
                default:
                    command = -1;
                    break;
            }
        } while (command != 0);
        System.out.println("До свидания");
        System.exit(0);
    }

    private static void transferFromAccToAcc() {
        System.out.println("Укажите номер паспорта клиента и счет с которого будет осуществлятся перевод");
        Customer customer1 = askForClientInfo();
        long accountId1 = askForAccountId();
        System.out.println("Укажиет сумму и валюту перевода");
        double amount = askForAmount();
        Currency currency = askForAmountCurrency();
        System.out.println("Укажиет номер паспорта клиента и счет на который будет осуществлен перевод");
        Customer customer2 = askForClientInfo();
        long accountId2 = askForAccountId();


        transfer(customer1, customer2, new BigDecimal(String.valueOf(amount)), currency, accountId1, accountId2);
    }

    private static void menuAddStorage() {
        Customer customer = askForClientInfo();
        clientInfo(customer);
        Long serviceAccount = askForServiceAccount(customer);
        if (serviceAccount == -1L) return;
        int typeStorage = askForStorageInfo();


        switch (typeStorage){
            case 0:
                break;
            case 1:
                askForShare(customer, accounts.get(serviceAccount));
                break;
            case 2:
                askForMetal();
                break;
        }

    }



    private static void askForShare(Customer customer, Account serviceAccount) {
        System.out.println("Введите название компании, акции которой будете хранить:");
        String sName = sc.nextLine();
        System.out.println("Введите цену за одну акцию в белорусских рублях");
        double amount = askForAmount();
        Shares shares = new Shares(sName, amount);
        System.out.println("Введите количесто акций, которые собираетесь приобрести");
        int sum = (int)askForAmount();
        DepositBox depositBox = new DepositBox(shares, serviceAccount);
        depositBox.deposit(sum);
        addStorage(customer, depositBox);


    }

    private static void askForMetal() {
    }

    private static Long askForServiceAccount(Customer customer) {
        Long accId = -1L;
        int type;

        do {
            System.out.println("Для обслуживания ячейки нужно указать счет");
            System.out.println("1. Выбрать существующий");
            System.out.println("2. Открыть новый счет");
            System.out.println("0. Отмена");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 0:
                    break;
                case 1:
                     accId = askForAccountId();
                     if (!clientAccounts.get(customer).contains(accounts.get(accId))){
                         System.out.println("Указанный вами счет не принадлежит выбранному клиенту");
                         accId = -1L;
                         type = -1;
                     }
                    break;
                case 2:
                    break;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return  accId;

    }

    private static int askForStorageInfo(){

        int type;

        do {
            System.out.println("Что будете хранить в ячейке");
            System.out.println("1. Акции");
            System.out.println("2. Драгоценные металлы");
            System.out.println("0. Отмена");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 0:
                case 1:
                case 2:
                    return type;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return type;
    }

    private static void registerNewClient() {

        boolean success;
        try {
            System.out.print("Введите имя = ");
            String name = sc.nextLine();
            System.out.print("Введите фамилию = ");
            String surname = sc.nextLine();
            System.out.print("Введите номер паспорта = ");
            String passportNumber = sc.nextLine();
            addClient(name, surname, passportNumber);
            success = true;
        } catch (Exception e) {
            success = false;
        }

        if (success) {
            System.out.println("Клиент успешно зарегистрирован");
        } else {
            System.out.println("Ошибка при регистрации клиента");
        }

    }

    private static void registerNewAccount() {

        Customer client = askForClientInfo();
        clientInfo(client);
        Currency currency = askForCurrency();
        int accountType = askForAccount();
        switch (accountType) {
            case 0:
                return;
            case 1:
                long number = addNewDebitAccount(client, currency);
                System.out.println("Открыт новый дебетовый счет под номером " + number);
                break;
            case 2:
                int percents = askForPercents();
                number = addNewPercentAccount(client, currency, percents);
                System.out.println("Открыт новый процентный счет под номером " + number);
                break;
            case 3:
                percents = askForPercents();
                number = addNewCreditAccount(client, currency, percents);
                System.out.println("Открыт новый кредитный счет под номером " + number);
                break;
        }

    }

    private static void withdrawAccountMoney() {
        Customer client = askForClientInfo();
        clientInfo(client);
        long accountId = askForAccountId();
        double amount = askForAmount();
        try {
            withdrawFromAccount(client, new BigDecimal(String.valueOf(amount)),accountId);
            System.out.println("Деньги сняты со счета успешно");
        } catch (NotEnoughValuableException e) {
            System.out.println("Недостаточно денег на счету");
        }
    }

    private static void checkAccountMoney() {
        Customer client = askForClientInfo();
        long accountId = askForAccountId();
        BigDecimal sum = getAccountBalance(client, accountId);
        System.out.println("Денег на счету = " + sum);
    }

    private static void depositAccountMoney() {
        Customer client = askForClientInfo();
        clientInfo(client);
        long accountId = askForAccountId();
        BigDecimal amount = new BigDecimal(String.valueOf(askForAmount()));
        depositOnAccount(client, amount, accountId);
        System.out.println("Баланс пополнен успешно");
    }

    private static double askForAmount() {
        double percents;

        do {
            System.out.print("Введите сумму = ");
            try {
                percents = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                percents = 0;
            }
        } while (percents <= 0);


        return percents;
    }

    private static long askForAccountId() {
        long id;

        do {
            System.out.print("Введите номер счета = ");
            try {
                id = Integer.parseInt(sc.nextLine());
                if (id > 0) {
                    if (searchAccountById(id)) {
                        return id;
                    } else {
                        id = 0;
                    }
                } else {
                    id = 0;
                }
            } catch (NumberFormatException e) {
                id = 0;
            }
        } while (id <= 0);


        return id;
    }

    private static int askForAccount() {

        int type;

        do {
            System.out.println("Выберите тип счета");
            System.out.println("1. Дебетовый");
            System.out.println("2. Процентный");
            System.out.println("3. Кредитный");
            System.out.println("0. Отмена");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return type;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return type;
    }

    private static Currency askForCurrency() {

        int type;

        do {
            System.out.println("Выберите валюту счета");
            System.out.println("1. BYN");
            System.out.println("2. EUR");
            System.out.println("3. USD");
            System.out.println("4. RUR");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 1:
                    return Currency.BYN;
                case 2:
                    return Currency.EUR;
                case 3:
                    return Currency.USD;
                case 4:
                    return Currency.RUR;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return Currency.BYN;
    }

    private static Currency askForAmountCurrency() {

        int type;

        do {
            System.out.println("Выберите валюту:");
            System.out.println("1. BYN");
            System.out.println("2. EUR");
            System.out.println("3. USD");
            System.out.println("4. RUR");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                type = -1;
            }
            switch (type) {
                case 1:
                    return Currency.BYN;
                case 2:
                    return Currency.EUR;
                case 3:
                    return Currency.USD;
                case 4:
                    return Currency.RUR;
                default:
                    type = -1;
                    break;
            }

        } while (type == -1);

        return Currency.BYN;
    }

    private static int askForPercents() {
        int percents;

        do {
            System.out.print("Введите процент = ");
            try {
                percents = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                percents = 0;
            }
        } while (percents <= 0);


        return percents;
    }

    private static Customer askForClientInfo() {

        Customer customer = null;
        boolean b = true;
        do {
            System.out.print("Введите номер паспорта = ");

                String passportNumber = sc.nextLine();
                boolean clientExists = searchClientByPassportNumber(passportNumber);
                if (clientExists) {
                    customer = new Customer("_", "_", passportNumber);
                    b = false;
                }else {
                    System.out.println("Клиента с таким номером паспорта не существует");
                }

        } while (b);

        return customer;
    }

    private static void clientInfo(Customer customer){

        for (Customer c1 : clients) {
            if (c1.equals(customer)){
                System.out.println(c1);
            }
        }



        for ( long id: clientAccounts.get(customer) ) {
            System.out.println(accounts.get(id)+"Id счета = "+id);
        }

    }

}
