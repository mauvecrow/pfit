package quangson.bradley.pfit.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import quangson.bradley.pfit.security.isd.ISDCaller;
import quangson.bradley.pfit.security.isd.ejb.SecurityFacade;

@Named("cRegister")
@RequestScoped
public class RegisterController {

    @Inject
    private SecurityFacade service;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    //ISDCaller fields
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private ISDCaller buildUser(){
        return new ISDCaller(
                username,
                passwordHash.generate(password.toCharArray()),
                email,
                firstName,
                lastName
        );
    }

    public String register(){
        var user = buildUser();
        service.registerUser(user, SecurityFacade.GroupNames.PLANNER);
        return "login";
    }
    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
