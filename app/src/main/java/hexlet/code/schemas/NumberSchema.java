package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public void required() {
        Predicate<Integer> isRequired = data -> data != null;
        addValidation("required", isRequired);
    }

    public void positive() {
        Predicate<Integer> isPositive = data -> (data == null || data > 0);
        addValidation("positive", isPositive);
    }

    public void range(int min, int max) {
        Predicate<Integer> isInRange = data -> (data >= min && data <= max);
        addValidation("range", isInRange);
    }
}
