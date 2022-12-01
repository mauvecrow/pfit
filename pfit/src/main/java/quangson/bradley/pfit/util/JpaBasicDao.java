package quangson.bradley.pfit.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

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

    @Override
    public T findOneByNamedQuery(String namedQuery, String paramName, String paramVal){
        return em.createNamedQuery(namedQuery, assignClass())
                .setParameter(paramName, paramVal)
                .getSingleResult();
    }

    @Override
    public List<T> findMany() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(assignClass());
        Root<T> root = q.from(assignClass());
        q.select(root);
        TypedQuery<T> tq = em.createQuery(q);
        return tq.getResultList();
    }
}
