package quangson.bradley.pfit.category;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class CategoryValue {

    @Id
    @Column(name = "cat_value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catValueId;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_type")
    private CategoryType categoryType;

    @Column(name = "value_code")
    private String valueCode;

    @Column(name = "value_description")
    private String valueDesc;

    public CategoryValue() {
    }

    public CategoryValue(CategoryType categoryType, String valueCode) {
        this.categoryType = categoryType;
        this.valueCode = valueCode;
    }

    public CategoryValue(CategoryType categoryType, String valueCode, String valueDesc) {
        this.categoryType = categoryType;
        this.valueCode = valueCode;
        this.valueDesc = valueDesc;
    }

    public int getCatValueId() {
        return catValueId;
    }

    public void setCatValueId(int catValueId) {
        this.catValueId = catValueId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    enum CategoryType {
        SIMPLE, MENU, HIERARCHY
    }
}
