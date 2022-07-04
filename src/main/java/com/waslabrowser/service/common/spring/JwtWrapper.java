package com.waslabrowser.service.common.spring;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtWrapper {

    public String getClaim(DecodedJWT token, String claim) {
        return token.getClaim(claim).asString();
    }

    public Boolean getClaimBool(DecodedJWT token, String isBlocked) {
        return token.getClaim(isBlocked).asBoolean();
    }
}
