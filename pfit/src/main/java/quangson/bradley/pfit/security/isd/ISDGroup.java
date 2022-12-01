package quangson.bradley.pfit.security.isd;

import jakarta.persistence.*;

@Entity
@Table(name = "id_store_groups")
public class ISDGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ids_group_id")
    private int groupId;

    @Column(name = "caller_id")
    private int callerId;

    @Column(name = "group_name")
    private String groupName;

    // getters and setters

    public int getGroupId() {
        return groupId;
    }

    //no setter for groupId

    public int getCallerId() {
        return callerId;
    }

    public void setCallerId(int callerId) {
        this.callerId = callerId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    // overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ISDGroup isdGroup)) return false;

        return groupId == isdGroup.groupId;
    }

    @Override
    public int hashCode() {
        return groupId;
    }

    @Override
    public String toString() {
        return "ISDGroup{" +
                "groupId=" + groupId +
                ", callerId=" + callerId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
