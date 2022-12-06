package quangson.bradley.pfit.security.isd.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import quangson.bradley.pfit.security.isd.ISDCaller;
import quangson.bradley.pfit.security.isd.ISDCallerDao;
import quangson.bradley.pfit.security.isd.ISDGroup;
import quangson.bradley.pfit.security.isd.ISDGroupDao;

@Stateless
public class SecurityFacade {

    @Inject
    private ISDGroupDao groupDao;

    @Inject
    private ISDCallerDao callerDao;


    public void registerUser(ISDCaller newUser, GroupNames group){
        createUser(newUser);
        var callerId = findCallerId(newUser.getUsername());

        switch(group){
            case PLANNER -> buildPlannerRole(callerId);
            case ADMIN -> buildAdminRole(callerId);
        }
    }
    private void createUser(ISDCaller newUser){
        callerDao.create(newUser);
    }

    private int findCallerId(String callerName){
        return callerDao.findOneByNamedQuery("findByUsername", "username", callerName)
                .getCallerId();
    }

    private void buildPlannerRole(int callerId){
        buildGroup(callerId, GroupNames.PLANNER.lowercaseName());
    }

    private void buildAdminRole(int callerId){
        buildGroup(callerId, GroupNames.ADMIN.lowercaseName());
    }

    private void buildGroup(int callerId, String groupName){
        ISDGroup newGroup = new ISDGroup();
        newGroup.setGroupName(groupName);
        newGroup.setCallerId(callerId);
        groupDao.create(newGroup);
    }

    public enum GroupNames {
        PLANNER, ADMIN;

        String lowercaseName() {
            return this.name().toLowerCase();
        }
    }
}
