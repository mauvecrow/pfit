package quangson.bradley.pfit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import quangson.bradley.pfit.transaction.Transaction;

@Path("/hello-world")
public class HelloResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces("text/plain")
    public String hello() {
        var result = (Transaction) em.createQuery("select t from Transaction t").getSingleResult();
        return result.toString();
    }
}