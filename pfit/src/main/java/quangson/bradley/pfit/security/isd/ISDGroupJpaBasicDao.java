package quangson.bradley.pfit.security.isd;

import jakarta.enterprise.context.RequestScoped;
import quangson.bradley.pfit.util.JpaBasicDao;

@RequestScoped
public class ISDGroupJpaBasicDao extends JpaBasicDao<ISDGroup> implements ISDGroupDao{
    @Override
    protected Class<ISDGroup> assignClass() {
        return ISDGroup.class;
    }

    @Override
    protected int getId(ISDGroup entity) {
        return entity.getGroupId();
    }
}
