package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {

    public void required() {
        Predicate<Map> isRequired = data -> data != null;
        addValidation("required", isRequired);
    }

    public void sizeof(int size) {
        Predicate<Map> isInSize = data -> (data.size() == size);
        addValidation("sizeof", isInSize);
    }

    public void shape(Map<String, BaseSchema<String>> schemasMap) {
        Predicate<Map> isInShape = data -> {
            for (var e : schemasMap.entrySet()) {
                if (!e.getValue().isValid(data.get(e.getKey()))) {
                    return false;
                }
            }
            return true;
        };
        addValidation("shape", isInShape);
    }
}
