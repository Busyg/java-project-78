package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> predicateMap = new HashMap<String, Predicate<T>>();

    public boolean isValid(Object obj) {
        for (var e : predicateMap.entrySet()) {
            if (!e.getValue().test((T) obj)) {
                return false;
            }
        }
        return true;
    }

    public void addValidation(String validation, Predicate<T> predicate) {
        predicateMap.put(validation, predicate);
    }
}
