package com.waslabrowser.service.common.spring.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class TokenFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // principal is set in here as a header or parameter. you need to find out
        // what it's named to extract it
        HttpServletRequest req = (HttpServletRequest) request;
        // in here, get your principal, and populate the auth object with
        // the right authorities
        final String authorization = ((HttpServletRequest) request).getHeader("Authorization");

        if (authorization != null && !authorization.isBlank()) {
            log.info("Token found in request");
            try {
                var token = authorization.replace("Bearer ", "");
                final DecodedJWT decode = JWT.decode(token);
                request.setAttribute("token", decode);
            } catch (Exception e) {
                log.error("couldn't decode token", e);
            }
        }else {
            log.info("no token, skipping...");
        }

        chain.doFilter(request, response);
    }
}
