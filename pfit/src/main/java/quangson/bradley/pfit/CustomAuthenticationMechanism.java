package quangson.bradley.pfit;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestScoped
@AutoApplySession
public class CustomAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpMessageContext httpMessageContext) throws AuthenticationException {
        Credential credential = httpMessageContext.getAuthParameters().getCredential();

        if(credential != null){
            return httpMessageContext.notifyContainerAboutLogin(
                    identityStore.validate(credential)
            );
        }
        else {
            return httpMessageContext.doNothing();
        }
    }
}
