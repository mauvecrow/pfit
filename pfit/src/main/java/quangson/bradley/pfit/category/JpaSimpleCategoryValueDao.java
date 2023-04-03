package quangson.bradley.pfit.category;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;
import java.util.List;



@SimpleCategory
@SessionScoped
public class JpaSimpleCategoryValueDao extends JpaBasicDao<SimpleCategoryValue> implements CategoryValueDao<SimpleCategoryValue>, Serializable {

    private EntityManager em;

    @PostConstruct
    void init(){
        em = getEntityManager();
    }

    @Override
    protected Class<SimpleCategoryValue> assignClass() {
        return SimpleCategoryValue.class;
    }

    @Override
    protected int getId(SimpleCategoryValue entity) {
        return entity.getCategoryId();
    }

}
