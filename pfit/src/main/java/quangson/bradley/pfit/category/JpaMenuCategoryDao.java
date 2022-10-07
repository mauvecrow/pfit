package quangson.bradley.pfit.category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMenuCategoryDao implements CategoryDao{

    @PersistenceContext
    EntityManager em;
    @Override
    public List<? extends Category> getCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MenuCategory> cq = cb.createQuery(MenuCategory.class);
        Root<MenuCategory> menuCategoryRoot = cq.from(MenuCategory.class);
        cq.select(menuCategoryRoot);
        return em.createQuery(cq)
                .getResultList();
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
