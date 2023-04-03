package quangson.bradley.pfit.category;

import quangson.bradley.pfit.util.BasicDao;

import java.util.List;

public interface CategorySetDao extends BasicDao<CategorySet> {

    List<CategorySet> getCategories(String owner);
}
