package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private boolean required = false;
    private int mapSize = -1;
    public Map<String, BaseSchema> shapesMap;

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.mapSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shapes) {
        this.shapesMap  = shapes;
        return this;
    }

    @Override
    public boolean isValid(Map data) {
        if (shapesMap != null && !isInShape(data)) {
            return false;
        }
        if (required && isRequired(data)) {
            return false;
        }
        if (mapSize > -1 && !isInSize(data)) {
            return false;
        }
        return true;
    }

    public boolean isRequired(Map data) {
        return data == null;
    }

    public boolean isInSize(Map data) {
        return data.size() == mapSize;
    }

    public boolean isInShape(Map data) {
        if (data == null) {
            return false;
        }
        for (var e : shapesMap.entrySet()) {
            if (!e.getValue().isValid(data.get(e.getKey()))) {
                return false;
            }
        }
        return true;
    }

}
