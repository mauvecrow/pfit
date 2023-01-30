package quangson.bradley.pfit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@ApplicationScoped
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:/MySqlDS",
        callerQuery = "select enc_password from id_store_callers where username = ?",
        groupsQuery = "select idsg.group_name from id_store_groups idsg, id_store_callers idsc " +
                "where idsg.caller_id = idsc.ids_caller_id and idsc.username = ?"
)
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
@FacesConfig
public class ApplicationConfig {
}
