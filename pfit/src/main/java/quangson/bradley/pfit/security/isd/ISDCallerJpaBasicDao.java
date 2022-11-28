package quangson.bradley.pfit.security.isd;

import jakarta.enterprise.context.SessionScoped;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;

@SessionScoped
public class ISDCallerJpaBasicDao extends JpaBasicDao<ISDCaller> implements ISDCallerDao, Serializable {
    @Override
    protected Class<ISDCaller> assignClass() {
        return ISDCaller.class;
    }

    @Override
    protected int getId(ISDCaller entity) {
        return entity.getCallerId();
    }
}
