package quangson.bradley.pfit.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class JpaBasicDao<T> implements BasicDao<T>{

    @PersistenceContext
    private EntityManager em;

    protected abstract Class<T> assignClass();

    protected abstract int getId(T entity);

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public T read(int basicId) {
        return em.find(assignClass(), basicId);
    }

    @Override
    public int update(T entity) {
        T attachedEntity = em.merge(entity);
        return getId(attachedEntity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }
}
