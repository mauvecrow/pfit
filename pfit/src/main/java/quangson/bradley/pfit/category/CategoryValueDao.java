package quangson.bradley.pfit.category;

import quangson.bradley.pfit.util.BasicDao;

import java.util.List;

public interface CategoryValueDao<T extends CategoryValue> extends BasicDao<T> {

    List<T> getValuesByCategory(int categoryId);
}
