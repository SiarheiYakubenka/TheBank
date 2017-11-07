package the_bank.deposit_boxes;

public class Shares implements Valuable {

    private final String  name;
    private double value;


    public Shares(String name, double value){
        this.name = name;
        this.value = value;

    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Shares{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
