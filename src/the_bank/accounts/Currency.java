package the_bank.accounts;

import java.math.BigDecimal;

public enum Currency {
    BYN("Белорусские рубли","1.9684", "0.01"),
    USD("Доллары США","1", "0.02"),
    EUR("Евро","0.855194", "0.02"),
    RUR("Российские рубли","57.720954", "0.5");

    private BigDecimal price, commission;
    private String description;

    Currency(String description, String price, String commission){
        this.price = new BigDecimal(price);
        this.commission = new BigDecimal(commission);
        this.description = description;
    }
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена валюты не может быть <= 0");
        }
        this.price = new BigDecimal(String.valueOf(price));
    }

    public BigDecimal getPrice() {
        return price;
    }
    public BigDecimal getCommission() {
        return commission;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
