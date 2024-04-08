package hexlet.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringSchema {
    private boolean required = false;
    private int minLength = -1;
    private String contains = "";

    public void required() {
        this.setRequired(true);
    }

    public void minLength(int length) {
        this.setMinLength(length);
    }

    public void contains(String substring) {
        this.setContains(substring);
    }

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
        return data.length() > minLength;
    }

    public boolean isContaining(String data) {
        return data.contains(contains);
    }
}
