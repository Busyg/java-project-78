package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void stringSchemaComplexTest() {
        var v = new Validator().string();
        v.required().minLength(5).contains("test");
        assertTrue(v.isValid("test5"));
        v.contains("test").minLength(10).required().minLength(2).contains("qwerty");
        assertTrue(v.isValid("qwerty"));
    }

    @Test
    public void numberSchemaRequiredTest() {
        var v = new Validator().number();
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(5));
        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(2));
    }

    @Test
    public void numberSchemaPositiveTest() {
        var v = new Validator().number();
        assertTrue(v.isValid(null));
        assertTrue(v.isValid(5));
        assertTrue(v.isValid(-2));
        v.positive();
        assertTrue(v.isValid(null));
        assertFalse(v.isValid(-2));
        assertTrue(v.isValid(2));
    }

    @Test
    public void numberSchemaRangeTest() {
        var v = new Validator().number();
        assertTrue(v.isValid(1));
        v.range(0, 2);
        assertTrue(v.isValid(0));
        assertTrue(v.isValid(2));
        assertFalse(v.isValid(3));
        v.range(-10, 0);
        assertTrue(v.isValid(-10));
        assertTrue(v.isValid(0));
        assertFalse(v.isValid(-11));
    }

    @Test
    public void numberSchemaComplexTest() {
        var v = new Validator().number();
        v.required().positive().range(0, 1);
        assertTrue(v.isValid(1));
        assertFalse(v.isValid(0));
    }

    @Test
    public void mapSchemaRequiredTest() {
        var v = new Validator().map();
        var data = new HashMap<String, String>();
        assertTrue(v.isValid(null));
        v.required();
        assertFalse(v.isValid(null));
        assertTrue(v.isValid(data));
    }

    @Test
    public void mapSchemaSizeTest() {
        var v = new Validator().map();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(v.isValid(data));
        v.sizeof(2);
        assertFalse(v.isValid(data));
        data.put("key2", "value2");
        assertTrue(v.isValid(data));
    }

    @Test
    public void mapSchemaShapeTest() {
        var v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
