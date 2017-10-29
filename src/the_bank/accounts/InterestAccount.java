package the_bank.accounts;

import java.math.BigDecimal;

public class InterestAccount extends Account {

    private BigDecimal interest;
    private BigDecimal sumInterest;

    public InterestAccount(float interest, BigDecimal amount, Currency currency) {
        super(amount, currency);
        this.interest = new BigDecimal(String.valueOf(interest));
        sumInterest = new BigDecimal("0");
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public void calcOfInterest(){
        BigDecimal sum = getBalance().multiply(interest).setScale(3, BigDecimal.ROUND_HALF_EVEN);
        sum = sum.divide( new BigDecimal("100"), 3, BigDecimal.ROUND_HALF_EVEN);
        sumInterest = sum.divide(new BigDecimal("365"), 2, BigDecimal.ROUND_HALF_UP);
    }

    public void  accrualOfInterest(){
        Operation.replenish(this, sumInterest, this.getCurrency());
    }


    @Override
    public String toString() {
        return String.format("%nТип счета: Процентный"+super.toString());
    }
}
