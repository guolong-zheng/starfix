package testgene;

import java.util.HashMap;
import java.util.Map;

public class Config {
    static Map<String, String> property = new HashMap<>();
    static Map<String, String[]> properties = new HashMap<>();

    public String getProperty(String prop) {
        return property.get(prop);
    }

    public String[] getStringArray(String prop) {
        return properties.get(prop);
    }
}
