package quangson.bradley.pfit.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "menu_category")
public class MenuCategory extends Category{

    @Transient
    public static final Category.Type categoryType = Type.Menu;

    @Column(name = "parent_id")
    private int parentId;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
