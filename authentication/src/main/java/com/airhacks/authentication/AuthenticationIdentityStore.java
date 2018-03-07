package com.airhacks.authentication;

import static java.util.Collections.singleton;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.VALIDATE;

@ApplicationScoped
public class AuthenticationIdentityStore implements IdentityStore {

    private final String password = "1234567";

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential usernamePassword = (UsernamePasswordCredential) credential;
            if (password != null && password.equals(usernamePassword.getPasswordAsString())) {
                return new CredentialValidationResult(usernamePassword.getCaller());
            } else {
                return INVALID_RESULT;
            }
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(VALIDATE);
    }
}
