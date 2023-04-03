package quangson.bradley.pfit.category;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;

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

}
