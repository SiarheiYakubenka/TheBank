package the_bank.customers;

import the_bank.accounts.Account;
import the_bank.deposit_boxes.DepositBox;
import the_bank.deposit_boxes.Valuable;

public class Customer {
    private final String name;
    private String secondName;
    private String passportID;
    private Account[] accounts;
    private DepositBox[] depositBoxes;

    public Customer(String name, String secondName, String passportID) {
        this.name = name;
        this.secondName = secondName;
        this.passportID = passportID;
    }

    public String getName() {
        return name;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getPassportID() {
        return passportID;
    }
    public Account[] getAccounts() {
        return accounts;
    }
    public DepositBox[] getDepositBoxes() {
        return depositBoxes;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }



    @Override
    public String toString() {
        return getName()+" "+getSecondName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return  false;
        }
        if (obj == this){
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return  false;
        }
        Customer other = (Customer) obj;
        return this.getPassportID().compareTo(other.getPassportID()) == 0;
    }

    public void addAccount(Account account){
        if (accounts == null)
            accounts = new Account[]{account};
        else{
            Account[] temp = new Account[accounts.length];
            accounts = new Account[accounts.length+1];
            for (int i = 0; i < temp.length ; i++) {
                accounts[i] = temp[i];
            }
            accounts[accounts.length-1] = account;
        }
    }
    public void addDepositBoxe(DepositBox depositBox){
        if (depositBoxes == null)
            depositBoxes = new DepositBox[]{depositBox};
        else{
            DepositBox[] temp = new DepositBox[depositBoxes.length];
            depositBoxes = new DepositBox[depositBoxes.length+1];
            for (int i = 0; i < temp.length ; i++) {
                depositBoxes[i] = temp[i];
            }
            depositBoxes[accounts.length-1] = depositBox;
        }
    }
}
