package quangson.bradley.pfit.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.ejb.TrxManager;

import java.io.Serializable;
import java.util.List;

@Named("cUser")
@SessionScoped
public class User implements Serializable {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    @Inject
    private TrxManager trxManager;

    private List<Transaction> transactions;

    private String username;
    private long monthlyOffset = 1;

    @PostConstruct
    public void init(){
        String rawName = securityContext.getCallerPrincipal()
                .getName();
        username = applyProperCasing(rawName);
        transactions = trxManager.getRecentMonthlyTrx(username,monthlyOffset);
    }


    public String logout() throws ServletException {
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest())
                .logout();
        return "/index.html?faces-redirect=true";
    }

    private String applyProperCasing(String name){
        return Character.toUpperCase(name.charAt(0)) +
                name.toLowerCase().substring(1);
    }

    public String getUsername() {
        return username;
    }

    public long getMonthlyOffset() {
        return monthlyOffset;
    }

    public void setMonthlyOffset(long monthlyOffset) {
        this.monthlyOffset = monthlyOffset;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
