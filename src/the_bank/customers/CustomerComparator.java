package the_bank.customers;

import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getSecondName().compareTo(o2.getSecondName());
    }
}
