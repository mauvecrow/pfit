package quangson.bradley.pfit.transaction;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import quangson.bradley.pfit.util.JpaBasicDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SessionScoped
public class JpaTransactionDao extends JpaBasicDao<Transaction> implements TransactionDao, Serializable {

    private EntityManager em;

    @PostConstruct
    void init(){
        em = getEntityManager();
    }

    @Override
    public List<Transaction> getTransactions(String trxOwner) {
        return em.createNamedQuery("ownerTrx", Transaction.class)
                .setParameter("owner",trxOwner)
                .getResultList();
    }

    @Override
    public List<Transaction> getRecentTransactions(String trxOwner, Date sinceDate) {
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
