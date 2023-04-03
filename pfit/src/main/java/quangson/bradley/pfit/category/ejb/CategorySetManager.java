package quangson.bradley.pfit.category.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import quangson.bradley.pfit.category.*;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CategorySetManager implements Serializable {

    @Inject
    private CategorySetDao categorySetDao;

    public List<CategorySet> getCategoriesByOwner(String owner){
        return categorySetDao.getCategories(owner);
    }

    public void addCategorySet(CategorySet newCatSet){
        categorySetDao.create(newCatSet);
    }

    public int updateCategorySet(CategorySet updatedCatSet){
        return categorySetDao.update(updatedCatSet);
    }

    public void removeCategorySet(CategorySet categorySet){
        categorySetDao.delete(categorySet);
    }
}
