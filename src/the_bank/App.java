package the_bank;

import the_bank.accounts.*;
import the_bank.accounts.Currency;

import java.math.BigDecimal;
import java.util.*;

import static the_bank.Bank.*;

public class App {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        final Timer time = new Timer();


        time.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                if(i == 30){
                    i = 0;
                    accrualInterest();
                }
                takeCommissionAndInterests();
                i++;
            }
        },  86_400_000, 86_400_000);

        showMenu();

        }

    static void showMenu(){
        while (true){
            System.out.println();
            System.out.println("(1)Клиенты " + getCustomersList().size());
            System.out.println("(2)Счета " + Account.getCount());
            System.out.println("(3)Ячейки " + (getCustomersList().size() - Account.getCount()));
            int s = Integer.parseInt(sc.nextLine());
            switch (s){
                case 1: showClientsMenu(); break;
                case 2: showAccountMenu(); break;
                case 3: showBoxMenu(); break;
            }
        }

    }

    static void showClientsMenu() {
        System.out.println();
        System.out.println("Клиенты " + getCustomersList().size() + " " +
                "Счета " + Account.getCount() + " " +
                "Ячейки " + (getCustomersList().size() - Account.getCount()));
        System.out.println("Добавить клиента(1)");
        System.out.println("Удалить клиента(2)");
        System.out.println("Информация о клиентах(3)");
        int s = Integer.parseInt(sc.nextLine());
        switch (s){
            case 1: addClientMenu(); break;
            case 2: delClient(); break;
            case 3: getInfo(); break;
        }

    }

    static void addClientMenu() {
        String name, secondName, id, typeAcc, amount, currency;
        Currency curr;
        Account acc;
        System.out.print("Введите имя клиента ");
        name = sc.nextLine();
        System.out.print("Введите фамилию клиента ");
        secondName = sc.nextLine();
        System.out.print("Введите id клиента ");
        id = sc.nextLine();
        System.out.println("Введите валюту счета для нового клиента.");
        System.out.println("(BYN, USD, EUR, RUR )");
        currency = sc.nextLine();
        curr = Currency.valueOf(currency);
        System.out.println("Введите сумму ");
        amount = sc.nextLine();
        System.out.println("Введите тип счета для нового клиента (Дебетовый(1), Кредитный(2), Процентный(3)");
        typeAcc = sc.nextLine();

        switch (typeAcc){
            case "1": acc = new DebitAccount(new BigDecimal(amount), curr); break;

            case "2": acc = new CreditAccount(10f, new BigDecimal(amount), curr); break;

            case "3": acc = new InterestAccount(10f, new BigDecimal(amount), curr); break;

            default:  acc = null;
        }
        try {
            addCustomer(name, secondName, id, acc);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            addClientMenu();
        }

    }

    static void delClient(){
        System.out.println();
        System.out.println("Введите id клиента, которого нужно удалить: ");
        String id = sc.nextLine();
        deleteClient(id);
    }

    static void getInfo(){
        getClientsInfo();
    }

    static void showAccountMenu(){
        System.out.println();
        System.out.println("Клиенты " + getCustomersList().size() + " " +
                "Счета " + Account.getCount() + " " +
                "Ячейки " + (getCustomersList().size() - Account.getCount()));
        System.out.println("Добавить счет(1)");
        System.out.println("Удалить счет(2)");
        int s = Integer.parseInt(sc.nextLine());
        switch (s){
            case 1:  break;
            case 2:  break;
        }

    }

    static void showBoxMenu(){
        System.out.println();
        System.out.println("Клиенты " + getCustomersList().size() + " " +
                "Счета " + Account.getCount() + " " +
                "Ячейки " + (getCustomersList().size() - Account.getCount()));
        System.out.println("Добавить ячейку(1)");
        System.out.println("Удалить ячейку(2)");
        int s = Integer.parseInt(sc.nextLine());
        switch (s){
            case 1:  break;
            case 2:  break;
        }

    }

}
