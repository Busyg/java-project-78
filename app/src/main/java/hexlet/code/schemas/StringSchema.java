package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> isRequired = data -> !(data == null || data.isEmpty());
        addValidation("required", isRequired);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> isLongerThan = data -> data != null && data.length() > length - 1;
        addValidation("minLength", isLongerThan);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> isContaining = data -> data.contains(substring);
        addValidation("contains", isContaining);
        return this;
    }
}
