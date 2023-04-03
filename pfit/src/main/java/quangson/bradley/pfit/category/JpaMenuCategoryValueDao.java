package quangson.bradley.pfit.category;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;
import java.util.List;

@MenuCategory
@SessionScoped
public class JpaMenuCategoryValueDao extends JpaBasicDao<MenuCategoryValue> implements CategoryValueDao<MenuCategoryValue>, Serializable {

    private EntityManager em;

    @PostConstruct
    void init(){
        em = getEntityManager();
    }

    @Override
    protected Class<MenuCategoryValue> assignClass() {
        return MenuCategoryValue.class;
    }

    @Override
    protected int getId(MenuCategoryValue entity) {
        return entity.getCategoryId();
    }

    @Override
    public List<MenuCategoryValue> getValuesByCategory(int categoryId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MenuCategoryValue> cq = cb.createQuery(MenuCategoryValue.class);
        Root<MenuCategoryValue> root = cq.from(MenuCategoryValue.class);

        cq.select(root).where(cb.equal(root.get("categoryId"), categoryId));

        return em.createQuery(cq).getResultList();
    }
}
