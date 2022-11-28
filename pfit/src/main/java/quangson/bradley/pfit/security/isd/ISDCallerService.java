package quangson.bradley.pfit.security.isd;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ISDCallerService {

    @Inject
    ISDCallerDao dao;

    public void addUser(ISDCaller newUser){
        dao.create(newUser);
    }

    public ISDCaller getUser(int callerId){
        return dao.read(callerId);
    }

    public int updateUser(ISDCaller user){
        return dao.update(user);
    }

    public void deleteUser(ISDCaller user){
        dao.delete(user);
    }
}
