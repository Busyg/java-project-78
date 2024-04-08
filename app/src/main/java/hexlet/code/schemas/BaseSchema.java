package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    public boolean isValid(T t) {
        return true;
    }
}
