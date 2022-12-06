package quangson.bradley.pfit.transaction.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.TransactionDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class TrxManager implements Serializable {

    @Inject
    TransactionDao dao;

    public void addTransaction(Transaction newTrx){
        dao.create(newTrx);
    }

    public List<Transaction> getAllTrx(String username){
        return dao.getTransactions(username);
    }

    public List<Transaction> getRecentMonthlyTrx(String username, long offset){
        return dao.getRecentTransactions(username, LocalDate.now().minusMonths(offset) );
    }

    /*
    To be implemented:
    Based on an uploaded csv
    public void import(){};

    Create csv
    public ? export(){};
     */

}
