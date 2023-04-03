package quangson.bradley.pfit.category.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import quangson.bradley.pfit.category.*;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CategoryValuesManager implements Serializable {

    @Inject
    @SimpleCategory
    private CategoryValueDao simpleCatValueDao;

    @Inject
    @MenuCategory
    private CategoryValueDao menuCatValueDao;

    public List<SimpleCategoryValue> getSimpleCategories(int categoryId){
        return simpleCatValueDao.getValuesByCategory(categoryId);
    }

    public List<MenuCategoryValue> getMenuCategories(int categoryId){
        return menuCatValueDao.getValuesByCategory(categoryId);
    }

    public void addSimpleValue(SimpleCategoryValue newValue){
        simpleCatValueDao.create(newValue);
    }

    public int updateSimpleValue(SimpleCategoryValue updatedValue){
        return simpleCatValueDao.update(updatedValue);
    }

    public void removeSimpleValue(SimpleCategoryValue value){
        simpleCatValueDao.delete(value);
    }

    public void addMenuValue(MenuCategoryValue newValue){
        menuCatValueDao.create(newValue);
    }

    public int updateMenuValue(MenuCategoryValue updatedValue){
        return menuCatValueDao.update(updatedValue);
    }

    public void removeMenuValue(MenuCategoryValue value){
        menuCatValueDao.delete(value);
    }
}
