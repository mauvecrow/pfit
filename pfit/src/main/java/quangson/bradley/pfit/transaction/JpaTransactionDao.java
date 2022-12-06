package quangson.bradley.pfit.transaction;

import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@SessionScoped
public class JpaTransactionDao extends JpaBasicDao<Transaction> implements TransactionDao, Serializable {

    private final EntityManager em;

    public JpaTransactionDao() {
        this.em = super.getEntityManager();
    }

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
    protected Class<Transaction> assignClass() {
        return Transaction.class;
    }

    @Override
    protected int getId(Transaction entity) {
        return entity.getTransactionId();
    }
}
