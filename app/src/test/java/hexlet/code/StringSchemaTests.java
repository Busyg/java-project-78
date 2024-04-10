package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testStringSchema() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Test"));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("Test"));
        schema.minLength(5);
        assertTrue(schema.isValid("Test5"));
        assertFalse(schema.isValid("Test"));
        schema.contains("Test5");
        assertTrue(schema.isValid("Test5"));
        assertFalse(schema.isValid("Test6"));
    }
}
