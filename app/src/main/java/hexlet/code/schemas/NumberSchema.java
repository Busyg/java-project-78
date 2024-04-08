package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean required = false;
    private boolean positive = false;
    private int[] range = {};

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.range  = new int[]{min, max};
        return this;
    }

    @Override
    public boolean isValid(Integer data) {
        if (required && isRequired(data)) {
            return false;
        }
        if (positive && !isPositive(data)) {
            return false;
        }
        if (range.length > 1 && !isInRange(data)) {
            return false;
        }
        return true;
    }

    public boolean isRequired(Integer data) {
        return data == null;
    }

    public boolean isPositive(Integer data) {
        return data != null && data > 0;
    }

    public boolean isInRange(Integer data) {
        return data >= this.range[0] && data <= this.range[1];
    }
}
