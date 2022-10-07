package quangson.bradley.pfit.transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionsDao {

    List<Transaction> getTransactions(String trxOwner);
    List<Transaction> getRecentTransactions(String trxOwner, LocalDate sinceDate);

    void create(Transaction newTransaction);
    Transaction read(int transactionId);
    int update(Transaction curTransaction);
    void delete(Transaction transaction);
}
