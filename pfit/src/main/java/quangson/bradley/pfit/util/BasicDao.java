package quangson.bradley.pfit.util;

import java.util.List;

public interface BasicDao<T> {

    void create(T entity);
    T read(int basicId);
    int update(T entity);
    void delete(T entity);

    T findOneByNamedQuery(String namedQuery, String paramName, String paramVal);
    List<T> findAll();

}
