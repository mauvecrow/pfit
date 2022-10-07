package quangson.bradley.pfit.category;

import java.util.List;

public interface CategoryDao {

    List<? extends Category> getCategories();
    void create(Category category);
    int update(Category category);
    void delete(Category category);
}
