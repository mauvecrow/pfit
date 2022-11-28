package quangson.bradley.pfit.util;

public interface BasicDao<T> {

    void create(T entity);
    T read(int basicId);
    int update(T entity);
    void delete(T entity);
}
