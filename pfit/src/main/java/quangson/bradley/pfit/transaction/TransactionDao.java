package quangson.bradley.pfit.transaction;

import quangson.bradley.pfit.util.BasicDao;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDao extends BasicDao<Transaction> {

    List<Transaction> getTransactions(String trxOwner);
    List<Transaction> getRecentTransactions(String trxOwner, LocalDate sinceDate);
}
