package quangson.bradley.pfit.security.isd;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "id_store_callers")
@NamedQueries({
        @NamedQuery(name = "findByUsername", query = "SELECT c from ISDCaller c where c.username = :username"),
})
public class ISDCaller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ids_caller_id")
    private int callerId;

    private String username;

    @Column(name = "enc_password")
    private String encPassword;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public ISDCaller() {
    }

    public ISDCaller(String username, String encPassword, String email, String firstName, String lastName) {
        this.username = username;
        this.encPassword = encPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getter and setters

    public int getCallerId() {
        return callerId;
    }

    public void setCallerId(int callerId) {
        this.callerId = callerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ISDCaller isdCaller)) return false;

        return callerId == isdCaller.callerId;
    }

    @Override
    public int hashCode() {
        return callerId;
    }

    @Override
    public String toString() {
        return "ISDCaller{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
