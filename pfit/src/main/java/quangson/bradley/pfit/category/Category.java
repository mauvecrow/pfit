package quangson.bradley.pfit.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public abstract class Category {

    @Id
    @Column(name = "category_id")
    private int categoryId;
    private String code;
    private String description;

    // getters and setters

    public int getCategoryId() {
        return categoryId;
    }
    // no setter for id

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Enum for category types
    enum Type {
        Simple, Menu, Hierarchy
    }
}
