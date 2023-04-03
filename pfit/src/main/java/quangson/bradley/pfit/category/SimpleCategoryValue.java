package quangson.bradley.pfit.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "simple_category_values")
public class SimpleCategoryValue extends CategoryValue {

    @Transient
    public static final CategoryType categoryType = CategoryType.SIMPLE;

    public SimpleCategoryValue() {
    }

    public SimpleCategoryValue(String valueCode) {
        super(CategoryType.SIMPLE, valueCode);
    }

    public SimpleCategoryValue(String valueCode, String valueDesc) {
        super(CategoryType.SIMPLE, valueCode, valueDesc);
    }
}
