package quangson.bradley.pfit.category;

import jakarta.persistence.*;

@Entity
@Table(name = "category_sets")
@NamedQueries({
        @NamedQuery(name = "catOwner", query = "select c from CategorySet c where c.owner = :owner")
})
public class CategorySet {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_description")
    private String categoryDesc;

    @Column(name = "category_type")
    private CategoryValue.CategoryType categoryType;

    private String owner;

    public CategorySet(){

    }
    public CategorySet(String categoryCode, String categoryDesc, CategoryValue.CategoryType categoryType, String owner) {
        this.categoryCode = categoryCode;
        this.categoryDesc = categoryDesc;
        this.categoryType = categoryType;
        this.owner = owner;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public CategoryValue.CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryValue.CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
