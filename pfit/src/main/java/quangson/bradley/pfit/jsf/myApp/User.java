package quangson.bradley.pfit.jsf.myApp;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;


@Named("cUser")
@SessionScoped
public class User implements Serializable {

    private static final Logger logger = LogManager.getLogger(User.class);
    @Inject
    private FacesContext facesContext;

    private String rawName;
    private String username;

    private static String applyProperCasing(String name){
        return Character.toUpperCase(name.charAt(0)) +
                name.toLowerCase().substring(1);
    }

    public String getUsername() {
        return null != rawName ? applyProperCasing(rawName) : "";
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String cardAction(String outcome){
        return outcome;
    }

    public String logout() throws ServletException {
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest())
                .logout();
        return "/index.html?faces-redirect=true";
    }

}
