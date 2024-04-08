package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class Tests {
    @Test
    public void stringSchemaRequiredTest() {
        var v = new Validator().string();
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(""));
        assertTrue(v.isValid("test"));
        v.required();
        assertFalse(v.isValid(null));
        assertFalse(v.isValid(""));
        assertTrue(v.isValid("test"));
    }

    @Test
    public void stringSchemaMinLengthTest() {
        var v = new Validator().string();
        assertTrue(v.isValid("test"));
        v.minLength(5);
        assertFalse(v.isValid("test"));
    }

    @Test
    public void stringSchemaContainsTest() {
        var v = new Validator().string();
        assertTrue(v.isValid("test"));
        v.contains("test");
        assertTrue(v.isValid("test"));
        v.contains("test2");
        assertFalse(v.isValid("test"));
    }
}
