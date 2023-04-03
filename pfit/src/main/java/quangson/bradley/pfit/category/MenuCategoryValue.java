package quangson.bradley.pfit.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "menu_category_values")
public class MenuCategoryValue extends CategoryValue {

    @Transient
    public static final CategoryType categoryType = CategoryType.MENU;

    public MenuCategoryValue(){
    }

    /**
     * Constructor to create new child value, no description provided
     * @param valueCode
     * @param parentId
     */
    public MenuCategoryValue(String valueCode, int parentId) {
        super(CategoryType.MENU, valueCode);
        this.parentId = parentId;
        this.childFlag = true;
    }

    /**
     * Constructor to create new child value with description
     * @param valueCode
     * @param parentId
     * @param valueDesc
     */
    public MenuCategoryValue(String valueCode, int parentId, String valueDesc) {
        super(CategoryType.MENU, valueCode, valueDesc);
        this.parentId = parentId;
        this.childFlag = true;
    }

    /**
     * Constructor to create new parent value, no description provided
     * @param valueCode
     */
    public MenuCategoryValue(String valueCode) {
        super(CategoryType.MENU, valueCode);
        this.childFlag = false;
    }

    /**
     * Constructor to create new parent value with description
     * @param valueCode
     * @param valueDesc
     */
    public MenuCategoryValue(String valueCode, String valueDesc) {
        super(CategoryType.MENU, valueCode);
        super.setValueDesc(valueDesc);
        this.childFlag = false;
    }

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "is_child")
    private boolean childFlag;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isChildFlag() {
        return childFlag;
    }

    public void setChildFlag(boolean childFlag) {
        this.childFlag = childFlag;
    }
}
