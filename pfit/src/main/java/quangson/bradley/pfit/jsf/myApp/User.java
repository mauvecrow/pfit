package quangson.bradley.pfit.jsf.myApp;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.ejb.TrxManager;
import java.util.List;

@Named("cUser")
@RequestScoped
public class User {

    @Inject
    private FacesContext facesContext;

    @Inject
    private TrxManager trxManager;

    private List<Transaction> transactions;

    private String username;
    private long monthlyOffset = 1;

    @PostConstruct
    public void init(){
        String rawName = facesContext.getExternalContext()
                .getUserPrincipal()
                .getName();
        username = applyProperCasing(rawName);
        transactions = trxManager.getRecentMonthlyTrx(username,monthlyOffset);
        transactions = List.of();
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

    public String cardAction(String outcome){
        return outcome;
    }
}
