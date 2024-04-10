package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testNumberSchema() {
        var schema = validator.number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(-1));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(1));
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(20));
        assertFalse(schema.isValid(-20));
        assertFalse(schema.isValid(0));
        schema.range(30, 40);
        assertTrue(schema.isValid(30));
        assertTrue(schema.isValid(40));
        assertFalse(schema.isValid(29));
        assertFalse(schema.isValid(41));
    }
}
