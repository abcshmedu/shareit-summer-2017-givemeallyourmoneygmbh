package edu.hm;

import edu.hm.shareit.service.AuthenticationService;
import edu.hm.shareit.service.AuthenticationServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/18/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */


public class AuthenticationFilter  implements javax.servlet.Filter {
    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String token = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

            // better injected
            //AuthenticationService authenticationService = new AuthenticationServiceImp();
            AuthenticationService authenticationService = new AuthenticationServiceImpl();
            boolean result = false;
            try {
                result = authenticationService.validateToken(token);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (result) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                if (servletResponse instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse
                            .setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
