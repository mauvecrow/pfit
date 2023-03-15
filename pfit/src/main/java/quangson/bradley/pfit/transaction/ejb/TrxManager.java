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

    TrxBuilder trxBuilder;
    public void addTransaction(Transaction newTrx){
        dao.create(newTrx);
    }

    public List<Transaction> getAllTrx(String username){
        return dao.getTransactions(username);
    }

    public List<Transaction> getRecentMonthlyTrx(String username, long offset){
        return dao.getRecentTransactions(username, LocalDate.now().minusMonths(offset) );
    }

    public TrxBuilder startTrxBuild(){
        if(trxBuilder == null){
            trxBuilder = new TrxBuilder();
        }
        return trxBuilder;
    }



    public static class TrxBuilder {
        private LocalDate trxDate;
        private String trxSource;
        private String trxVendor;
        private double trxAmt;
        private String notes = null;
        private String trxOwner = null;
        private TrxBuilder(){
            trxDate = LocalDate.now();
            trxSource = "Manual entry";
            trxVendor = "unknown";
            trxAmt = 0.0;
        }

        public TrxBuilder date(LocalDate date){
            this.trxDate = date;
            return this;
        }

        public TrxBuilder source(String source){
            this.trxSource = source;
            return this;
        }

        public TrxBuilder vendor(String vendor){
            this.trxVendor = vendor;
            return this;
        }

        public TrxBuilder amount(double amount){
            this.trxAmt = amount;
            return this;
        }

        public TrxBuilder notes(String notes){
            this.notes = notes;
            return this;
        }

        public TrxBuilder owner(String owner){
            this.trxOwner = owner;
            return this;
        }

        public Transaction build(){
            var newTrx = new Transaction();
            newTrx.setTransactionDate(trxDate);
            newTrx.setTransactionSource(trxSource);
            newTrx.setVendor(trxVendor);
            newTrx.setAmount(trxAmt);
            newTrx.setNotes(notes);
            newTrx.setTrxOwner(trxOwner);
            return newTrx;
        }
    }

    /*
    To be implemented:
    Based on an uploaded csv
    public void import(){};

    Create csv
    public ? export(){};
     */

}
