package quangson.bradley.pfit.transaction;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class JpaTransactionsDao implements TransactionsDao{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Transaction> getTransactions(String trxOwner) {
        return em.createNamedQuery("ownerTrx", Transaction.class)
                .setParameter("owner",trxOwner)
                .getResultList();
    }

    @Override
    public List<Transaction> getRecentTransactions(String trxOwner, LocalDate sinceDate) {
        return em.createNamedQuery("recentOwnerTrx", Transaction.class)
                .setParameter("owner", trxOwner)
                .setParameter("date", sinceDate)
                .getResultList();
    }

    @Override
    public void create(Transaction newTransaction) {
        em.persist(newTransaction);
    }

    @Override
    public Transaction read(int transactionId) {
        return em.find(Transaction.class, transactionId);
    }

    @Override
    public int update(Transaction curTransaction) {
        var attachedEntity =
                em.merge(curTransaction);
        return attachedEntity.getTransactionId();
    }

    @Override
    public void delete(Transaction transaction) {
        em.remove(em.merge(transaction));
    }
}
