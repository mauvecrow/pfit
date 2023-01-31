package quangson.bradley.pfit.security;


import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Named("cLogin")
@RequestScoped
public class LoginController {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;


    private String username;

    private String password;

    public void login() throws IOException {
        ExternalContext ec = facesContext.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        HttpServletResponse resp = (HttpServletResponse) ec.getResponse();

        AuthenticationParameters params =
                AuthenticationParameters.withParams()
                        .credential(
                                new UsernamePasswordCredential(username,password)
                        );
//                        .newAuthentication(false);
        AuthenticationStatus outcome = securityContext.authenticate(req, resp, params);

        switch(outcome){
            case SEND_CONTINUE -> facesContext.responseComplete();
            case SEND_FAILURE -> facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid credentials", null));
            case SUCCESS -> ec.redirect(ec.getRequestContextPath() + "/myApp/transactions.xhtml"); //throws IOException
        }
    }

    public String logout() throws ServletException {
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest())
                .logout();
        return "/index.html?faces-redirect=true";
    }

    public String returnHome() {
        return "/myApp/transactions.xhtml?faces-redirect=true";
    }

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
}
