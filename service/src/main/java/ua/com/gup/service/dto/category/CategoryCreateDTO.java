package ua.com.gup.service.dto.category;


import java.util.HashMap;
import java.util.Map;

public class CategoryCreateDTO {

    private int parent;

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> description = new HashMap<>();

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }
}
