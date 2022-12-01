package quangson.bradley.pfit.security.isd;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import quangson.bradley.pfit.util.BasicDao;

@Stateless
public class SecurityFacade {

    @Inject
    @ISDGroupQ
    private BasicDao<ISDGroup> groupDao;

    @Inject
    @ISDCallerQ
    private BasicDao<ISDCaller> callerDao;


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
