package quangson.bradley.pfit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import quangson.bradley.pfit.entities.Transactions;

@Path("/hello-world")
public class HelloResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces("text/plain")
    public String hello() {
        var result = (Transactions) em.createQuery("select t from Transactions t").getSingleResult();
        return result.toString();
    }
}