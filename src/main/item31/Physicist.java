package item31;

public class Physicist extends Person {
    private Integer dissertationCount;
    public Physicist(String name, Integer age, Integer dissertationCount) {
        super(name, age);
        this.dissertationCount = dissertationCount;
    }
}
//public interface Physicist extends Scientist {
//
//}
