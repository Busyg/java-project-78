package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private boolean required = false;
    private int minLength = -1;
    private String contains = "";

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(String data) {
        if (required && isRequired(data)) {
            return false;
        }
        if (minLength > -1 && !isLongerThan(data)) {
            return false;
        }
        if (!contains.isEmpty() && !isContaining(data)) {
            return false;
        }
        return true;
    }

    public boolean isRequired(String data) {
        return data == null || data.isEmpty();
    }

    public boolean isLongerThan(String data) {
        return data.length() > minLength - 1;
    }

    public boolean isContaining(String data) {
        return data.contains(contains);
    }
}
