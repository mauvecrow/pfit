package quangson.bradley.pfit.jsf.myApp;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.ejb.TrxManager;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

@Named("cTrxDetails")
@ViewScoped
public class TrxDetails implements Serializable {

    private List<Transaction> transactions;
    private LinkedHashMap<Transaction, Boolean> editableTrxMap;

    @Inject
    private TrxManager trxManager;

    @Inject
    User user;

    @PostConstruct
    private void init(){
        transactions = trxManager.getAllTrx(user.getRawName());
        editableTrxMap = new LinkedHashMap<>();
        transactions.forEach(trx -> editableTrxMap.put(trx, Boolean.FALSE));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public LinkedHashMap<Transaction, Boolean> getEditableTrxMap() {
        return editableTrxMap;
    }

    public void toggleEdit(Transaction trx){
        editableTrxMap.put(trx, !editableTrxMap.get(trx));
    }
}
