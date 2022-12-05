package quangson.bradley.pfit.security.isd;

import jakarta.enterprise.context.RequestScoped;
import quangson.bradley.pfit.util.JpaBasicDao;

@RequestScoped
public class ISDCallerJpaBasicDao extends JpaBasicDao<ISDCaller> implements ISDCallerDao{
    @Override
    protected Class<ISDCaller> assignClass() {
        return ISDCaller.class;
    }

    @Override
    protected int getId(ISDCaller entity) {
        return entity.getCallerId();
    }
}
