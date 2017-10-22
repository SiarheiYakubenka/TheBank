package the_bank.accounts;

import java.math.BigDecimal;

public enum Currency {
    BYN("Белорусские рубли","1.9684", "0.01"),
    USD("Доллары США","1", "0.02"),
    EUR("Евро","0.855194", "0.02"),
    RUR("Российские рубли","57.720954", "0.5");

    private BigDecimal rate, commission;
    private String description;

    Currency(String description, String rate, String commission){
        this.rate = new BigDecimal(rate);
        this.commission = new BigDecimal(commission);
        this.description = description;
    }

    public BigDecimal getRate() {
        return rate;
    }
    public BigDecimal getCommission() {
        return commission;
    }
    public String getDescription() {
        return description;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

}
