package ua.com.gup.service.dto.category.tree;


import ua.com.gup.domain.category.attribute.CategoryAttributeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryAttributeDTO {
    private int code;
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private CategoryAttributeType type;
    private CategoryAttributeValidatorDTO validator;
    private Set<CategoryAttributeValueDTO> values;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Map<String, String> getUnit() {
        return unit;
    }

    public void setUnit(Map<String, String> unit) {
        this.unit = unit;
    }

    public CategoryAttributeType getType() {
        return type;
    }

    public void setType(CategoryAttributeType type) {
        this.type = type;
    }

    public CategoryAttributeValidatorDTO getValidator() {
        return validator;
    }

    public void setValidator(CategoryAttributeValidatorDTO validator) {
        this.validator = validator;
    }

    public Set<CategoryAttributeValueDTO> getValues() {
        return values;
    }

    public void setValues(Set<CategoryAttributeValueDTO> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryAttributeDTO that = (CategoryAttributeDTO) o;

        return code == that.code;
    }

    @Override
    public int hashCode() {
        return code;
    }
}