package quangson.bradley.pfit.category;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;



@Stateless
public class JpaSimpleCategoryDao implements CategoryDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<? extends Category> getCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SimpleCategory> cq = cb.createQuery(SimpleCategory.class);
        Root<SimpleCategory> simpleCategoryRoot = cq.from(SimpleCategory.class);
        cq.select(simpleCategoryRoot);

        TypedQuery<SimpleCategory> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public void create(Category category) {
        em.persist(category);
    }

    @Override
    public int update(Category category) {
        var attachedEntity = em.merge(category);
        return attachedEntity.getCategoryId();
    }

    @Override
    public void delete(Category category) {
        em.remove(em.merge(category));
    }
}
