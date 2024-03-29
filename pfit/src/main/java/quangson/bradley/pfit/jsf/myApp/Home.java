package quangson.bradley.pfit.jsf.myApp;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quangson.bradley.pfit.transaction.Transaction;
import quangson.bradley.pfit.transaction.ejb.TrxManager;

import java.util.List;


@Named("cHome")
@RequestScoped
public class Home {

    private static final Logger logger = LogManager.getLogger(Home.class);
    List<Transaction> recentTrx;
    private long offset = 6;
    @Inject
    private TrxManager trxManager;

    @Inject
    User user;

    @PostConstruct
    private void init(){
        recentTrx = trxManager.getRecentMonthlyTrx(user.getRawName(), offset);
    }

    public List<Transaction> getRecentTrx() {
        return recentTrx;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
