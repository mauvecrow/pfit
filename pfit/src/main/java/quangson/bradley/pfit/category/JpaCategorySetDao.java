package quangson.bradley.pfit.category;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class JpaCategorySetDao extends JpaBasicDao<CategorySet> implements Serializable, CategorySetDao {

    private EntityManager em;

    @PostConstruct
    void init(){
        em = super.getEntityManager();
    }

    @Override
    protected Class<CategorySet> assignClass() {
        return CategorySet.class;
    }

    @Override
    protected int getId(CategorySet entity) {
        return entity.getCategoryId();
    }

    @Override
    public List<CategorySet> getCategories(String owner) {
        return em.createNamedQuery("catOwner", CategorySet.class)
                .setParameter("owner", owner)
                .getResultList();
    }
}
