package quangson.bradley.pfit.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "simple_category")
public class SimpleCategory extends Category{

    @Transient
    public static final Category.Type categoryType = Type.Simple;

    private int group;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

}
